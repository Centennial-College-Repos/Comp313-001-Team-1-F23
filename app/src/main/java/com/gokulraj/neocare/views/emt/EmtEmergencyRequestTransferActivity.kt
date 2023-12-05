package com.gokulraj.neocare.views.emt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityEmtEmergencyrequestTransferBinding

/**
 *
 * The layout will provide a user interface for
 * transferring an emergency request to another EMT or a medical organization.
 * */
class EmtEmergencyRequestTransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtEmergencyrequestTransferBinding
    private var requestId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtEmergencyrequestTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestId = intent.getStringExtra("REQUEST_ID")

        binding.btnTransfer.setOnClickListener {
            transferRequest(requestId)
        }
    }

    private fun transferRequest(requestId: String?) {
        // Logic to transfer the request
        // Placeholder for Firebase or other backend logic
        Toast.makeText(this, "Request $requestId transferred", Toast.LENGTH_SHORT).show()
        // Redirect to the main request list or appropriate screen
        startActivity(Intent(this, EmtEmergencyRequestActivity::class.java))
        finish()
    }
}
