package com.gokulraj.neocare.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This activity page protocols and information for emergencies
 *  */

class ProtocolActivity : AppCompatActivity() {

    private lateinit var conditionSpinner: Spinner
    private lateinit var viewGuidelinesButton: View
    private lateinit var guidelinesTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_protocol)

        conditionSpinner = findViewById(R.id.conditionSpinner)
        viewGuidelinesButton = findViewById(R.id.viewGuidelinesButton)
        guidelinesTextView = findViewById(R.id.guidelinesTextView)

        val conditions = resources.getStringArray(R.array.medical_conditions)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conditions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        conditionSpinner.adapter = adapter

        viewGuidelinesButton.setOnClickListener {
            val selectedCondition = conditionSpinner.selectedItem.toString()
            val guidelines = getGuidelinesForCondition(selectedCondition)
            guidelinesTextView.text = guidelines
        }

        conditionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Clear the guidelines text when a new condition is selected
                guidelinesTextView.text = ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle when nothing is selected (optional)
            }
        }
    }

    private fun getGuidelinesForCondition(condition: String): String {
        return when (condition) {
            "Heart Disease" -> "Guidelines for Heart Disease:\nIt's essential to consult with a healthcare professional or cardiologist for personalized treatment recommendations. Moreover, the specific guidelines may change over time, so it's crucial to refer to the most recent guidelines from reputable sources such as the American College of Cardiology (ACC) or the American Heart Association (AHA). These organizations regularly publish and update guidelines for the treatment of various heart diseases."
            "Accident" -> "Guidelines for Accident:\nMedical guidelines and protocols for responding to accidents vary depending on the type and severity of the accident. Accidents can encompass a wide range of situations, such as motor vehicle accidents, workplace accidents, falls, sports injuries, and more."
            "Burns" -> "Guidelines for Burns:\nIt's essential to emphasize that burn care should be individualized based on the circumstances and medical assessment. Seek professional medical help for burns covering a large area, burns caused by chemicals or electricity, or any burns to the face, hands, feet, or genital area. Timely and appropriate treatment can significantly improve the outcome for burn injuries."
            else -> "No guidelines available for this condition"
        }
    }
}
