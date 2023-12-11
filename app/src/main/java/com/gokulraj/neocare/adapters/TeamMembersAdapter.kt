package com.gokulraj.neocare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.R
import com.gokulraj.neocare.database.TeamMembersEntity

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * Adapter for TeamMember details
 *  */

class TeamMembersAdapter(private val developerList: List<TeamMembersEntity>) :
    RecyclerView.Adapter<TeamMembersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val devNameTextView: TextView = itemView.findViewById(R.id.tvDevName)
        val devIdTextView: TextView = itemView.findViewById(R.id.tvDevId)
        val devUnitTextView: TextView = itemView.findViewById(R.id.tvDevUnit)
        val devImageView:ImageView=itemView.findViewById(R.id.ivDevImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_teammembersdetails, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val developer = developerList[position]
        val devname =  holder.devNameTextView.text.toString()
        val devid= holder.devIdTextView.text.toString()
        val devunit= holder.devUnitTextView.text.toString()

        holder.devNameTextView.text = devname + developer.dev_name
        holder.devIdTextView.text = devid+ developer.dev_id.toString()
        holder.devUnitTextView.text = devunit+developer.dev_unit

        holder.devImageView.setImageResource(developer.dev_image)
    }

    override fun getItemCount(): Int {
        return developerList.size
    }
}