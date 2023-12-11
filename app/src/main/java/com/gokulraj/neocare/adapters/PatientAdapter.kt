package com.gokulraj.neocare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.database.Patient
import com.gokulraj.neocare.databinding.ActivityPatientItemBinding

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * Adapter for patient details
 *  */

class PatientAdapter(
    private val patients: List<Patient>,
    private val onClick: (Patient) -> Unit
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = ActivityPatientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patients[position]
        holder.bind(patient, onClick)
    }

    override fun getItemCount(): Int = patients.size

    class PatientViewHolder(private val binding: ActivityPatientItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(patient: Patient, onClick: (Patient) -> Unit) {
            binding.apply {
                tvPatientName.text = patient.fullName
                tvPatientCondition.text = patient.condition
                tvPatientLocation.text = patient.location
                itemView.setOnClickListener { onClick(patient) }
            }
        }
    }
}