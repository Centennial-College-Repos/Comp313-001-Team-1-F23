package com.gokulraj.neocare.views.emt

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityEmtMedicalguidelinesBinding


/**
 *
 * This activity could be used by EMTs to access various medical guidelines.
 * The interaction with an database or API that provides these guidelines.
 * */
class MedicalGuidelinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtMedicalguidelinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtMedicalguidelinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGuidelinesSpinner()
    }

    private fun setupGuidelinesSpinner() {
        // Example conditions (usually fetched from a database or API)
        val medicalConditions = arrayOf("Cardiac Arrest", "Stroke", "Trauma", "Respiratory Distress")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, medicalConditions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.apply{
            spinnerMedicalConditions.adapter = adapter
            btnViewGuidelines.setOnClickListener {
                val selectedCondition = spinnerMedicalConditions.selectedItem.toString()
                fetchAndDisplayGuidelines(selectedCondition)
            }
        }
    }

    private fun fetchAndDisplayGuidelines(condition: String) {
        // Placeholder for fetching guidelines based on the selected condition
        // For demonstration, display a toast message
        Toast.makeText(this, "Fetching guidelines for: $condition", Toast.LENGTH_SHORT).show()
        val guidelines = when (condition) {
            "Cardiac Arrest" -> "Guideline for Cardiac Arrest: Immediate CPR, defibrillation, and emergency medical response."
            "Stroke" -> "Guideline for Stroke: Quick assessment, maintaining airways, and rapid transport to a medical facility."
            "Trauma" -> "Guideline for Trauma: Stop bleeding, prevent shock, and immediate transportation to the hospital."
            "Respiratory Distress" -> "Guideline for Respiratory Distress: Ensure open airways, oxygen therapy, and continuous monitoring."
            else -> "No specific guidelines available for the selected condition."
        }

        // Displaying the guidelines in the TextView
        binding.tvGuidelines.text = guidelines
    }
}
