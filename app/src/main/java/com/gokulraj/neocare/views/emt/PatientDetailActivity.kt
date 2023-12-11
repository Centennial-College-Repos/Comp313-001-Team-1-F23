package com.gokulraj.neocare.views.emt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityPatientDetailBinding

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This activity page displays patient details
 *  */

class PatientDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patientId = intent.getStringExtra("PATIENT_ID")
        loadPatientDetails(patientId)
    }

    private fun loadPatientDetails(patientId: String?) {
        // Fetch patient details from a database or backend service using patientId
        // Placeholder for data fetching logic
        // Example data for UI demonstration
        binding.apply{
            tvPatientNameDetail.text = "John Doe"
            tvPatientConditionDetail.text = "Chronic"
            tvPatientLocationDetail.text = "123 Main St"
        }
    }
}
