package com.gokulraj.neocare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.database.EmergencyRequest
import com.gokulraj.neocare.databinding.ActivityEmtEmergencyrequestItemBinding

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * Adapter for Emergency Request details
 *  */

class EmergencyRequestAdapter(
    private val emergencyRequests: List<EmergencyRequest>,
    private val onEmergencyRequestClicked: (EmergencyRequest) -> Unit
): RecyclerView.Adapter<EmergencyRequestAdapter.EmergencyRequestViewHolder>() {

    class EmergencyRequestViewHolder(
        private val binding: ActivityEmtEmergencyrequestItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(emergencyRequest: EmergencyRequest, onEmergencyRequestClicked: (EmergencyRequest) -> Unit) {
            binding.apply {
                tvDetailedRequestPatientName.text = emergencyRequest.patientName
                tvDetailedRequestAddress.text = emergencyRequest.location
                tvDetailedRequestDescription.text = emergencyRequest.emergencyDetails

                itemView.setOnClickListener {
                    onEmergencyRequestClicked(emergencyRequest)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyRequestViewHolder {
        val binding = ActivityEmtEmergencyrequestItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return EmergencyRequestViewHolder(binding)
    }

    override fun getItemCount(): Int = emergencyRequests.size

    override fun onBindViewHolder(holder: EmergencyRequestViewHolder, position: Int) {
        holder.bind(emergencyRequests[position], onEmergencyRequestClicked)
    }
}