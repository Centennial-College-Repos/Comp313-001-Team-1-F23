package com.gokulraj.neocare.views.emt

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityEmtMedicaleducationBinding

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * Access educational resources.
 * Retrieve a list of medical education topics and provide an interface for selecting and viewing details about each topic.
 *
 * */

class  MedicalEducationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtMedicaleducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtMedicaleducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEducationTopicsSpinner()
    }

    private fun setupEducationTopicsSpinner() {
        val educationTopics = arrayOf("CPR Training", "Advanced Life Support", "Trauma Management", "Pediatric Emergency Care")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, educationTopics)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEducationTopics.adapter = adapter

        binding.btnViewTopicDetails.setOnClickListener {
            val selectedTopic = binding.spinnerEducationTopics.selectedItem.toString()
            displayTopicDetails(selectedTopic)
        }
    }

    private fun displayTopicDetails(topic: String) {
        // Placeholder for fetching education resources based on the selected topic
        // For demonstration, display a toast message
        val details = when (topic) {
            "CPR Training" -> "Comprehensive CPR training covering the latest guidelines."
            "Advanced Life Support" -> "Modules on advanced techniques in life support and emergency response."
            "Trauma Management" -> "In-depth training on managing traumatic injuries in emergency situations."
            "Pediatric Emergency Care" -> "Specialized training for handling pediatric emergencies."
            else -> "No specific details available for the selected topic."
        }

        // Displaying the details in a TextView
        binding.tvTopicDetails.text = details
    }
}
