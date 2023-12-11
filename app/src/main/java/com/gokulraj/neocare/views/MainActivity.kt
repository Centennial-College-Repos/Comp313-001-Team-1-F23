package com.gokulraj.neocare.views


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.gokulraj.neocare.databinding.ActivityMainBinding

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * This is the main activity of the application which launches all other pages
 *  */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*binding.startButton.setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }

         */

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomePageActivity::class.java))
        }, 3000)


    }



}