package com.gokulraj.neocare.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.R
import com.gokulraj.neocare.adapters.TeamMembersAdapter
import com.gokulraj.neocare.database.TeamMembersEntity

/**
 * This activity page displays team member details
 *  */

class TeamMembersActivity:AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var teamMembersAdapter: TeamMembersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teammembers)

        recyclerView = findViewById(R.id.rvAbout)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadDevelopers()

    }

    private fun loadDevelopers() {
        val developerList = ArrayList<TeamMembersEntity>()
        developerList.add(TeamMembersEntity("Gokulraj Venugopal", "301202722", "Health Informatics Technology",R.drawable.gokul_image))
        developerList.add(TeamMembersEntity("Vinny Mariam Vinu", "301234317", "Health Informatics Technology",R.drawable.vinny_image))
        developerList.add(TeamMembersEntity("Husna Fatima", "300993436", "Health Informatics Technology",R.drawable.husna_image))
        developerList.add(TeamMembersEntity("Lea Marie Magbalot", "301145757", "Health Informatics Technology",R.drawable.lea_image))
        developerList.add(TeamMembersEntity("Zhiyang Song", "301167073", "Health Informatics Technology",R.drawable.zong_image))


        teamMembersAdapter = TeamMembersAdapter(developerList)
        recyclerView.adapter = teamMembersAdapter


    }


}