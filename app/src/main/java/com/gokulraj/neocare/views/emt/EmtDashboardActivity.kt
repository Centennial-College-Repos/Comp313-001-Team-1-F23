package com.gokulraj.neocare.views.emt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R
import com.google.firebase.auth.FirebaseAuth

/**
 * Dashboard for EMT
 *  Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 *  */

class EmtDashboardActivity : AppCompatActivity() {

    private lateinit var emergencyRequestButton: Button
    private lateinit var viewPatientsButton: Button
    private lateinit var updateProfileButton: Button
    private lateinit var communicationToolButton: Button
    private lateinit var medicalGuidelinesButton: Button
    private lateinit var medicalEducationButton: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emt_dashboard)

        initializeButtons()
        setupButtonListeners()
    }

    private fun initializeButtons() {
        emergencyRequestButton = findViewById(R.id.requestButton)
        viewPatientsButton = findViewById(R.id.viewPatientsButton)
        updateProfileButton = findViewById(R.id.updateProfileButton)
        communicationToolButton = findViewById(R.id.communicationToolButton)
        medicalGuidelinesButton = findViewById(R.id.medicalGuidelinesButton)
        medicalEducationButton = findViewById(R.id.medicalEducationButton)
        logoutButton = findViewById(R.id.logoutButton)
    }

    private fun setupButtonListeners() {

        updateProfileButton.setOnClickListener {
            startActivity(Intent(this, EmtProfileActivity::class.java))
        }

        emergencyRequestButton.setOnClickListener {
            startActivity(Intent(this, EmtEmergencyRequestActivity::class.java))
        }

        viewPatientsButton.setOnClickListener {
            startActivity(Intent(this, ViewPatientsActivity::class.java))
        }

        communicationToolButton.setOnClickListener {
            startActivity(Intent(this, CommunicationToolActivity::class.java))
        }

        medicalGuidelinesButton.setOnClickListener {
            startActivity(Intent(this, MedicalGuidelinesActivity::class.java))
        }

        medicalEducationButton.setOnClickListener {
            startActivity(Intent(this, MedicalEducationActivity::class.java))
        }

        logoutButton.setOnClickListener {
            logoutEmt()
        }
    }

    private fun logoutEmt() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, EmtLoginActivity::class.java))
        finish()
    }
}