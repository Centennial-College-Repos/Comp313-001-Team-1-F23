package com.gokulraj.neocare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.R
import com.gokulraj.neocare.database.AmbulanceEntity

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * Adapter for ambulance details
 *  */


class AmbulanceAdapter(private val ambulanceList: List<AmbulanceEntity>) :
    RecyclerView.Adapter<AmbulanceAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ambulanceNumberTextView: TextView = itemView.findViewById(R.id.tvRegNum)
        val ambulanceDriverTextView: TextView = itemView.findViewById(R.id.tvDriverName)
        val locationTextView: TextView = itemView.findViewById(R.id.tvLocation)
        val ambulanceImageView: ImageView =itemView.findViewById(R.id.ivAmbulanceImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_ambulancedetails, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ambulance = ambulanceList[position]
        val ambulanceregnum =  holder.ambulanceNumberTextView.text.toString()
        val ambulancename= holder.ambulanceDriverTextView.text.toString()
        val location= holder.locationTextView.text.toString()

        holder.ambulanceNumberTextView.text = ambulanceregnum + ambulance.amb_regnum
        holder.ambulanceDriverTextView.text = ambulancename+ ambulance.amb_driver
        holder.locationTextView.text = location+ambulance.amb_location

        holder.ambulanceImageView.setImageResource(ambulance.amb_image)
    }

    override fun getItemCount(): Int {
        return ambulanceList.size
    }

}