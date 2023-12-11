package com.gokulraj.neocare.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.database.User
import com.gokulraj.neocare.databinding.ActivityProfileUpdateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileUpdateBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference.child("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding = ActivityProfileUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchUserProfile()
        binding.btnSave.setOnClickListener {
            saveProfileData()
        }
    }

    private fun fetchUserProfile() {
        val currentUserEmail = firebaseAuth.currentUser?.email

        if (currentUserEmail != null) {
            databaseReference.orderByChild("email").equalTo(currentUserEmail)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (userSnapshot in snapshot.children) {
                                val user = userSnapshot.getValue(User::class.java)
                                if (user != null) {
                                    binding.apply {
                                        profileFullName.setText(user.fullName ?: "")
                                        profileHealthNumber.setText(user.healthCardNumber ?: "")
                                        profilePassword.setText(user.password ?: "")
                                    }
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            this@ProfileActivity,
                            "Error fetching profile: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

    private fun saveProfileData() {
        val updatedFullName = binding.profileFullName.text.toString().trim()
        val updatedHealthNumber = binding.profileHealthNumber.text.toString().trim()
        val updatedPassword = binding.profilePassword.text.toString().trim()

        val currentUser = firebaseAuth.currentUser
        val uid = currentUser?.uid

        if (uid != null) {
            // Update user profile data
            if(updatedPassword.length>5 && updatedHealthNumber.length>11)
            {
                updateProfileData(uid, updatedFullName, updatedHealthNumber,updatedPassword)

                // Check if the password needs to be updated
                if (updatedPassword.isNotEmpty()) {
                    // Update password using Firebase Authentication method
                    currentUser.updatePassword(updatedPassword)
                        .addOnCompleteListener { passwordUpdateTask ->
                            if (passwordUpdateTask.isSuccessful) {
                                Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Failed to update password: ${passwordUpdateTask.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } else {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Toast.makeText(this, "HealthCard Number should be atleast 12 digits", Toast.LENGTH_SHORT).show()

        }
    }


    private fun updateProfileData(
        uid: String,
        updatedFullName: String,
        updatedHealthNumber: String,
        updatedPassword: String
    ) {
        val userUpdateMap = mapOf(
            "fullName" to updatedFullName,
            "healthCardNumber" to updatedHealthNumber,
            "password" to updatedPassword
        )

        val databaseReference = FirebaseDatabase.getInstance().reference.child("users")
        databaseReference.child(uid).updateChildren(userUpdateMap)
            .addOnCompleteListener { updateTask ->
                if (updateTask.isSuccessful) {
                    Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Failed to update profile: ${updateTask.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
