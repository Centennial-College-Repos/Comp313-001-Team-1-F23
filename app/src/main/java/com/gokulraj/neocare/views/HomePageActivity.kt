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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.system.exitProcess

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
       // println(userType)
        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        // Retrieve user type from Firebase


       /* binding.requestButton.setOnClickListener {
            // Handle the emergency services request here
            // You can start an emergency service activity, make a call, or perform any other action as needed.
            // For this example, we'll open the phone dialer to call an emergency number (e.g., 911).
            val phoneNumber = "tel:911"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            startActivity(intent)
        }
        */

        
        /*binding.registrationLink.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }
            */



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

        binding.logOutLink.setOnClickListener {
            logoutUser()
        }

    }

    /*
    private fun startVoiceCommand() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        try {
            startActivityForResult(intent, VOICE_COMMAND_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            // Handle the case where voice recognition is not supported on the device
            Toast.makeText(this, "Voice recognition not supported on this device", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the result of the voice command
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VOICE_COMMAND_REQUEST_CODE && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val command = result?.get(0) // Get the recognized voice command

            // Check the recognized voice command and perform the action
            if (command.equals("call ambulance", ignoreCase = true)) {
                // You can initiate a call to ambulance services here
                val phoneNumber = "tel:911"
                val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                startActivity(callIntent)
            } else {
                Toast.makeText(this, "Voice command not recognized or supported.", Toast.LENGTH_SHORT).show()
            }
        }
    }

     */



    private fun handleMenuOptions(userType: String) {
        when (userType) {
            "Patient" -> showPatientOptions()
            "Healthcare Professional" -> showHealthcareProfessionalOptions()
            // Add other user types as needed
            else -> {
                // Default actions if needed
            }
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
    }

    private fun showHealthcareProfessionalOptions() {
        binding.updateProfileLink.visibility = View.VISIBLE
        binding.teamMembersLink.visibility = View.VISIBLE
        binding.aboutUsLink.visibility = View.VISIBLE
        binding.firstAidLink.visibility = View.VISIBLE // Show this option for healthcare professionals
        binding.logOutLink.visibility=View.VISIBLE
        binding.ambulanceDetails.visibility=View.VISIBLE
        binding.protocols.visibility=View.VISIBLE
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