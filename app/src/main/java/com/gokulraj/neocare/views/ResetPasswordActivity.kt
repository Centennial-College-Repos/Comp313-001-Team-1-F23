package com.gokulraj.neocare.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityResetpasswordBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * In response to Reset Email Link
 * This page handles reset Password function
 * */
class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetpasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = FirebaseAuth.getInstance().currentUser
        binding.btnResetPassword.setOnClickListener {
            val newPassword = binding.newPasswordEditText.text.toString().trim()
            if (newPassword.isNotEmpty()) {
                user?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                        // Redirect to login or another appropriate activity
                    } else {
                        Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter a new password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
