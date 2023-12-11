package com.gokulraj.neocare.views

import

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
            // You can now send this feedback to your server or handle it as needed
            handleFeedbackSubmission(feedbackText)
        }

        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.create().show()
    }

    private fun handleFeedbackSubmission(feedbackText: String) {
        // You can implement logic to send feedback to your server or store it locally
        // For example, you might make a network request to a feedback API
        // or save it to a local database.

        // For now, let's just log the feedback
        Log.d("Feedback", "User provided feedback: $feedbackText")

        // You may want to show a thank you message to the user or handle it accordingly
        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show()
    }
}
