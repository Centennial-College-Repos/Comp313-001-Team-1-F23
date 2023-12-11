package com.gokulraj.neocare.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityProfileUpdateBinding

class ProfileActivity: AppCompatActivity() {

    private lateinit var binding:ActivityProfileUpdateBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileUpdateBinding.inflate(layoutInflater)
                setContentView(binding.root)
    }

}