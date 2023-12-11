package com.gokulraj.neocare.views


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityHomepageBinding
import com.gokulraj.neocare.views.emt.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.system.exitProcess

/**
 * This is the home page for the users: EMT and patient
 * It displays the contents based on the access
 *  */

class HomePageActivity:AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding

    // Firebase variables
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Retrieve the user type from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val userType = sharedPreferences.getString("USER_TYPE", "default_value")

        if (userType != null) {
            handleMenuOptions(userType)
        }

        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.teamMembersLink.setOnClickListener {
            startActivity(Intent(this, TeamMembersActivity::class.java))
        }

        binding.aboutUsLink.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
        binding.firstAidLink.setOnClickListener {
            startActivity(Intent(this, FirstAidView::class.java))
        }
        binding.feedbackLink.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }
        binding.updateProfileLink.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

        binding.ambulanceDetails.setOnClickListener {
            startActivity(Intent(this, AmbulanceActivity::class.java))
        }

        binding.protocols.setOnClickListener {
            startActivity(Intent(this, ProtocolActivity::class.java))
        }

        binding.emergencyRequest.setOnClickListener {
            startActivity(Intent(this,EmtEmergencyRequestActivity::class.java))
        }

        binding.viewPatients.setOnClickListener {
            startActivity(Intent(this,ViewPatientsActivity::class.java))
        }

        binding.communicationTool.setOnClickListener {
            startActivity(Intent(this,CommunicationToolActivity::class.java))
        }

        binding.medicalGuidelines.setOnClickListener {
            startActivity(Intent(this,MedicalGuidelinesActivity::class.java))
        }

        binding.medicalEducation.setOnClickListener {
            startActivity(Intent(this,MedicalEducationActivity::class.java))
        }


        binding.logOutLink.setOnClickListener {
            logoutUser()
        }

    }

    private fun handleMenuOptions(userType: String) {
        when (userType) {
            "Patient" -> showPatientOptions()
            "Healthcare Professional" -> showHealthcareProfessionalOptions()
        }
    }

    private fun showPatientOptions() {
        binding.updateProfileLink.visibility = View.VISIBLE
        binding.teamMembersLink.visibility = View.VISIBLE
        binding.aboutUsLink.visibility = View.VISIBLE
        binding.logOutLink.visibility=View.VISIBLE
        binding.feedbackLink.visibility=View.VISIBLE
        binding.firstAidLink.visibility = View.VISIBLE // Hide this option for patients
        binding.ambulanceDetails.visibility=View.GONE
        binding.protocols.visibility=View.GONE
        binding.emergencyRequest.visibility=View.GONE
        binding.viewPatients.visibility=View.GONE
        binding.communicationTool.visibility=View.GONE
        binding.medicalGuidelines.visibility=View.GONE
        binding.medicalEducation.visibility=View.GONE
    }

    private fun showHealthcareProfessionalOptions() {
        binding.updateProfileLink.visibility = View.VISIBLE
        binding.teamMembersLink.visibility = View.VISIBLE
        binding.aboutUsLink.visibility = View.VISIBLE
        binding.firstAidLink.visibility = View.VISIBLE // Show this option for healthcare professionals
        binding.logOutLink.visibility=View.VISIBLE
        binding.ambulanceDetails.visibility=View.VISIBLE
        binding.protocols.visibility=View.VISIBLE
        binding.emergencyRequest.visibility=View.VISIBLE
        binding.viewPatients.visibility=View.VISIBLE
        binding.communicationTool.visibility=View.VISIBLE
        binding.medicalGuidelines.visibility=View.VISIBLE
        binding.medicalEducation.visibility=View.VISIBLE
    }


    private fun logoutUser() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("USER_TYPE") // Remove the user type
        editor.apply()

        //FirebaseAuth.getInstance().signOut() // Sign out the user from Firebase Authentication
        Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show()

        // Create a delayed action to start the LoginActivity after 2 seconds
        Handler().postDelayed({
            val intent = Intent(this@HomePageActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Finish the current activity
        }, 2000) // 2000 milliseconds = 2 seconds
    }


    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                // If the user clicks "Yes", exit the app
                finishAffinity()
                exitProcess(0)
            }
            .setNegativeButton("No", null)
            .show()

    }
}