package com.gokulraj.neocare.views.emt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.database.EmergencyTechnician
import com.gokulraj.neocare.databinding.ActivityEmtLoginBinding
import com.gokulraj.neocare.views.ForgetPasswordActivity
import com.gokulraj.neocare.views.LoginActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
/**
 * Login activity for EMT
 *  */
class EmtLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtLoginBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference.child("emergency_technicians")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            loginEmt()
        }

        binding.registerLink.setOnClickListener {
            startActivity(Intent(this, EmtRegistrationActivity::class.java))
            finish()
        }

        binding.forgotPasswordLink.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
            finish()
        }
    }

    private fun loginEmt() {
        val email = binding.emailAddressEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password must not be empty", Toast.LENGTH_SHORT).show()
            return
        }

        databaseReference.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var loginSuccessful = false

                    for (userSnapshot in snapshot.children) {
                        val userData = userSnapshot.getValue(EmergencyTechnician::class.java)
                        if (userData != null && userData.password == password && userData.userType == "EMT") {
                            loginSuccessful = true
                            startActivity(Intent(this@EmtLoginActivity, EmtDashboardActivity::class.java))
                            finish()
                            break
                        }
                    }
                    // To avoid login misjudgement
                    if (!loginSuccessful) {
                        Toast.makeText(this@EmtLoginActivity, "Login Failed! Please check your credentials.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@EmtLoginActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
