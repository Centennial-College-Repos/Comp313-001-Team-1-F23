package com.gokulraj.neocare.views.emt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityEmtCommunicationToolBinding

class CommunicationToolActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtCommunicationToolBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtCommunicationToolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCallPatient.setOnClickListener {
            val phone = binding.etPatientPhoneNumber.text.toString()
            if (phone.isNotEmpty()) {
                makePhoneCall(phone)
            }
        }

        binding.btnSendMessage.setOnClickListener {
            val message = binding.etMessage.text.toString()
            if (message.isNotEmpty()) {
                sendMessage(message)
            }
        }
    }

    private fun makePhoneCall(number: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
    }

    // Implement logic to send a message to the patient or other healthcare professionals
    // This could involve sending an SMS, an email, or using another messaging service
    private fun sendMessage(message: String) {
        val phone = binding.etPatientPhoneNumber.text.toString()
        if (phone.isNotEmpty()) {
            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phone, null, message, null, null)
                Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        } else {
            Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_SHORT).show()
        }
    }
}