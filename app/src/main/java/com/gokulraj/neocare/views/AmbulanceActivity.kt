package com.gokulraj.neocare.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.R
import com.gokulraj.neocare.adapters.AmbulanceAdapter
import com.gokulraj.neocare.database.AmbulanceEntity

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This activity page displays ambulance details
 *  */

class AmbulanceActivity:AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ambulanceAdapter: AmbulanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambulance)

        recyclerView = findViewById(R.id.rvAmbulance)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadAmbulance()

    }

    private fun loadAmbulance() {
        val ambulanceList = ArrayList<AmbulanceEntity>()
        ambulanceList.add(AmbulanceEntity("ON MXJ11R", "John Doe", "Scarborough",R.drawable.ambulance))
        ambulanceList.add(AmbulanceEntity("ON 123MJJ", "Felix Goke", "Brantford",R.drawable.ambulance))
        ambulanceList.add(AmbulanceEntity("AB HG77HF", "George Mann", "Calgary",R.drawable.ambulance))
        ambulanceList.add(AmbulanceEntity("ON KDJ566", "Dylan Kora", "Hamilton",R.drawable.ambulance))
        ambulanceList.add(AmbulanceEntity("BC UJH87R", "Grace Kin", "Vancouver",R.drawable.ambulance))


        ambulanceAdapter = AmbulanceAdapter(ambulanceList)
        recyclerView.adapter = ambulanceAdapter


    }



}