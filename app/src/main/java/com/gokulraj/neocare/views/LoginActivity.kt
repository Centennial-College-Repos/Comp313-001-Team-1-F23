package com.gokulraj.neocare.views

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gokulraj.neocare.database.User
import com.gokulraj.neocare.databinding.ActivityLoginBinding
import com.gokulraj.neocare.views.emt.EmtLoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.system.exitProcess

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This handles the login of EMT and Patients into the application
 *  */


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    //Declaring a request code for voice command

    private val VOICE_COMMAND_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")


        binding.requestImageView.setOnClickListener {
            // Handle the emergency services request here
            // You can start an emergency service activity, make a call, or perform any other action as needed.
            // For this example, we'll open the phone dialer to call an emergency number (e.g., 911).
            val phoneNumber = "tel:911"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            startActivity(intent)
        }

        // Attach a click listener to the voice command button
        binding.voiceCommandButton.setOnClickListener {
            startVoiceCommand()
        }


        binding.btnLogin.setOnClickListener{
            val email = binding.emailAddressEt.text.toString()
            val password =  binding.passwordEt.text.toString()

            if (validateEmail() && validatePassword()){
                loginUser(email, password)
            } else {
                Toast.makeText(this@LoginActivity, "All fields are mandatory.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerRedirect.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            finish()
        }

        binding.emtLogin.setOnClickListener{
            startActivity(Intent(this@LoginActivity, EmtLoginActivity::class.java))
            finish()
        }

        binding.forgetRedirect.setOnClickListener{
            startActivity(Intent(this@LoginActivity, ForgetPasswordActivity::class.java))
            finish()
        }
    }

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

    private fun validateEmail(): Boolean {
        var errorMsg: String? = null
        val value = binding.emailAddressEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Email is required."
        } else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMsg = "Email address is invalid."
        }

        if (errorMsg != null){
            binding.emailAddressTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validatePassword(): Boolean {
        var errorMsg: String? = null
        val value = binding.passwordEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Password is required."
        } else if(value.length < 5){
            errorMsg = "Password must be at least 5 characters long."
        }

        if (errorMsg != null){
            binding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun loginUser(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Authentication successful, get the authenticated user
                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                    if (firebaseUser != null) {
                        val uid = firebaseUser.uid

                        getUserTypeFromDatabase(uid) { userType ->
                            // Use userType here (e.g., update UI or perform further actions)
                            println(userType)

                            // Storing the UID and userType in SharedPreferences
                            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("UID", uid)
                            editor.putString("EMAIL", email)
                            editor.putString("USER_TYPE", userType) // Add this line
                            editor.apply()

                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                    } else {
                        // Handle the case where the authenticated user is null
                        Toast.makeText(this@LoginActivity, "User not authenticated", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Authentication failed
                    Toast.makeText(this@LoginActivity, "Login Failed! Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun getUserTypeFromDatabase(uid: String, callback: (String) -> Unit) {
        val userTypeReference = firebaseDatabase.reference.child("users").child(uid).child("userType")

        userTypeReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userType = if (snapshot.exists()) {
                    snapshot.getValue(String::class.java) ?: "default"
                } else {
                    "default"
                }
                callback(userType)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
                Toast.makeText(this@LoginActivity, "Error retrieving user type: ${error.message}", Toast.LENGTH_SHORT).show()
                callback("default") // Return default value in case of an error
            }
        })
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