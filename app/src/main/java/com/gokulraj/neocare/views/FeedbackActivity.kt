package com.gokulraj.neocare.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R


/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This activity page allows to provide feedback to EMT and services
 *  */
class FeedbackActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        // Assume you have a button for providing feedback
        val feedbackButton: Button = findViewById(R.id.feedbackButton)

        feedbackButton.setOnClickListener {
            showFeedbackDialog()
        }
    }

    private fun showFeedbackDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Provide Feedback")
        alertDialogBuilder.setMessage("Please share your feedback on the service with the app and the EMT team.")

        val inputEditText = EditText(this)
        alertDialogBuilder.setView(inputEditText)

        alertDialogBuilder.setPositiveButton("Submit") { _, _ ->
            val feedbackText = inputEditText.text.toString()
            handleFeedbackSubmission(feedbackText)
        }

        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.create().show()
    }

    private fun handleFeedbackSubmission(feedbackText: String) {
        Log.d("Feedback", "User provided feedback: $feedbackText")

        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show()
    }
}
