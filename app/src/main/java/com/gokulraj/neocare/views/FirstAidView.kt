package com.gokulraj.neocare.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This activity page displays first aid information needed in case of emergencies
 *  */

class FirstAidView : AppCompatActivity() {
    private lateinit var emergencyDescriptionTextView: TextView
    private lateinit var firstAidInfoTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstaid)

        emergencyDescriptionTextView = findViewById(R.id.emergencyDescriptionTextView)
        firstAidInfoTextView = findViewById(R.id.firstAidInfoTextView)
        val emergencyRadioGroup = findViewById<RadioGroup>(R.id.emergencyRadioGroup)

        emergencyRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            val emergency = selectedRadioButton.text.toString()
            displayEmergencyDetails(emergency)
        }
    }

    private fun displayEmergencyDetails(emergency: String) {
        val description = getEmergencyDescription(emergency)
        val firstAidInfo = getFirstAidInfo(emergency)

        emergencyDescriptionTextView.text = "Description: $description"
        firstAidInfoTextView.text = "First Aid Info: $firstAidInfo"
    }

    private fun getEmergencyDescription(emergency: String): String {
        return when (emergency) {
            "Fire" -> "A fire emergency involves the presence of an uncontrolled fire. Stay low and evacuate the building."
            "Earthquake" -> "An earthquake is the shaking of the surface of the Earth. Take cover under sturdy furniture."
            "Flood" -> "A flood emergency is caused by an overflow of water onto normally dry land. Seek higher ground."
            "Tornado" -> "A tornado is a rapidly rotating column of air that is capable of causing destruction. Seek shelter in a basement or small, windowless room."
            "Hurricane" -> "A hurricane is a severe storm with strong winds and heavy rainfall. Follow evacuation orders and stay indoors."
            "Medical Emergency" -> "A medical emergency involves a sudden, unexpected illness or injury. Call 911 and provide first aid."
            else -> "No description available"
        }
    }

    private fun getFirstAidInfo(emergency: String): String {
        return when (emergency) {
            "Fire" -> "For fire-related injuries, use a fire blanket or extinguisher if safe to do so and seek medical help."
            "Earthquake" -> "For earthquake-related injuries, provide first aid as needed and call for medical assistance."
            "Flood" -> "For flood-related injuries, make sure the area is safe and provide first aid. Seek medical assistance if required."
            "Tornado" -> "For tornado-related injuries, provide first aid as needed. If there are severe injuries, call 911."
            "Hurricane" -> "For hurricane-related injuries, provide first aid and call for medical assistance when necessary."
            "Medical Emergency" -> "For medical emergencies, assess the situation and provide appropriate first aid. Call 911 for assistance."
            else -> "No first aid information available"
        }
    }
}
