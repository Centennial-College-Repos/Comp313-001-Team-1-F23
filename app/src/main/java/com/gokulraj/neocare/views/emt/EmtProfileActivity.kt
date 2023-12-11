package com.gokulraj.neocare.views.emt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.database.EmergencyTechnician
import com.gokulraj.neocare.databinding.ActivityEmtProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * Do not forget to add current EMT to Firebase Authentication
 * */
class EmtProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference.child("emergency_technicians")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding = ActivityEmtProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchEmtProfile()
        binding.btnSave.setOnClickListener {
            saveProfileData()
        }
    }

    private fun fetchEmtProfile() {
        //Using email for matching
        val currentUserEmail = firebaseAuth.currentUser?.email
        if (currentUserEmail != null) {
            databaseReference.orderByChild("email").equalTo(currentUserEmail).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val technician = userSnapshot.getValue(EmergencyTechnician::class.java)
                            if (technician != null) {
                                binding.apply{
                                    etFullName.setText(technician.fullName ?: "")
                                    etPhoneNumber.setText(technician.contactNumber ?: "")
                                    etSpecialization.setText(technician.specialization ?: "")
                                }
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@EmtProfileActivity, "Error fetching profile: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun saveProfileData() {
        val updatedFullName = binding.etFullName.text.toString().trim()
        val updatedPhoneNumber = binding.etPhoneNumber.text.toString().trim()
        val updatedSpecialization = binding.etSpecialization.text.toString().trim()
        val updatedPassword = binding.etPassword.text.toString().trim()

        val currentUser = firebaseAuth.currentUser
        val userEmail = currentUser?.email

        // Dedicated for password not empty
        if (userEmail != null && updatedPassword.isNotEmpty()) {
            currentUser.updatePassword(updatedPassword).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to update password: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val userUpdateMap = mapOf(
            "fullName" to updatedFullName,
            "contactNumber" to updatedPhoneNumber,
            "specialization" to updatedSpecialization
        )

        databaseReference.child(currentUser?.uid ?: "").updateChildren(userUpdateMap).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to update profile: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
