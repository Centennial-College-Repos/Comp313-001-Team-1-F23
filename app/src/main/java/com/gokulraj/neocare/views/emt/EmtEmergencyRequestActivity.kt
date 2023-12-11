package com.gokulraj.neocare.views.emt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gokulraj.neocare.adapters.EmergencyRequestAdapter
import com.gokulraj.neocare.database.EmergencyRequest
import com.gokulraj.neocare.databinding.ActivityEmtEmergencyrequestBinding

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This class will manage a list of emergency requests and will open a detailed view when an item is clicked.
 * The XML layout will contain a RecyclerView to display the list of emergency requests.
 * */
class EmtEmergencyRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtEmergencyrequestBinding
    private lateinit var requestAdapter: EmergencyRequestAdapter
    private var emergencyRequests = mutableListOf<EmergencyRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtEmergencyrequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadEmergencyRequests()
    }

    private fun setupRecyclerView() {
        requestAdapter = EmergencyRequestAdapter(emergencyRequests) { request ->
            val intent = Intent(this, EmtEmergencyRequestDetailActivity::class.java).apply {
                putExtra("REQUEST_ID", request.requestId)
            }
            startActivity(intent)
        }
        binding.rvEmergencyRequests.apply {
            layoutManager = LinearLayoutManager(this@EmtEmergencyRequestActivity)
            adapter = requestAdapter
        }
    }

    private fun loadEmergencyRequests() {
        // Fetch emergency requests from Firebase (Placeholder for Firebase logic)
        emergencyRequests.add(EmergencyRequest("REQ123", "John Doe", "123 Main St", "Severe allergic reaction"))
        emergencyRequests.add(EmergencyRequest("REQ124", "Jane Smith", "456 Oak St", "Broken leg"))
        requestAdapter.notifyDataSetChanged()
    }
}
