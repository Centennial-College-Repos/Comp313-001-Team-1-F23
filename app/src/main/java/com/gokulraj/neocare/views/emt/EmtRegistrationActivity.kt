package com.gokulraj.neocare.views.emt

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * Registration Activity for EMT
 *  */

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.database.EmergencyTechnician
import com.gokulraj.neocare.databinding.ActivityEmtRegistrationBinding
import com.gokulraj.neocare.views.LoginActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EmtRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtRegistrationBinding
    private val databaseReference = FirebaseDatabase.getInstance().reference.child("emergency_technicians")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if (validateInput()) {
                checkEmailThenRegister()
            }
        }

        binding.loginLink.setOnClickListener {
            startActivity(Intent(this, EmtLoginActivity::class.java))
            finish()
        }

        binding.regularLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun registerEmt() {
        val emtID = databaseReference.push().key
        val fullName = binding.fullNameEt.text.toString().trim()
        val email = binding.emailAddressEt.text.toString().trim()
        val contactNumber = binding.contactNumberEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()
        val specialization = binding.specializationEt.text.toString().trim()

        val newEmt = EmergencyTechnician(emtID, fullName, contactNumber, email, password, specialization, "EMT")
        databaseReference.child(emtID!!).setValue(newEmt).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, EmtLoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validation logic remains the same
    private fun validateInput(): Boolean {
        val fullName = binding.fullNameEt.text.toString().trim()
        val email = binding.emailAddressEt.text.toString().trim()
        val contactNumber = binding.contactNumberEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()
        val specialization = binding.specializationEt.text.toString().trim()

        if (fullName.isEmpty()) {
            binding.fullNameEt.error = "Full name is required"
            return false
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailAddressEt.error = "Valid email is required"
            return false
        }
        if (contactNumber.length < 10){
            binding.contactNumberEt.error = "Contact Number must be at least 10 characters"
            return false
        }
        if (password.length < 6) {
            binding.passwordEt.error = "Password must be at least 6 characters"
            return false
        }
        if (specialization.isEmpty()) {
            binding.specializationEt.error = "Specialization is required"
            return false
        }

        return true
    }

    private fun checkEmailThenRegister() {
        val email = binding.emailAddressEt.text.toString().trim()
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    // Email is not in use, proceed with registration
                    registerEmt()
                } else {
                    // Email already in use
                    Toast.makeText(this@EmtRegistrationActivity, "Email already in use", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EmtRegistrationActivity, "Error checking email: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        val intent = Intent(this, EmtLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
