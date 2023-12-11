package com.gokulraj.neocare.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R
import com.gokulraj.neocare.databinding.ActivityAboutusBinding

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This About Us activity page displays the details of the company NeoCare
 *  */

class AboutUsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAboutusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)
        binding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvAboutDetails.text = getString(R.string.about_us)

    }
}
