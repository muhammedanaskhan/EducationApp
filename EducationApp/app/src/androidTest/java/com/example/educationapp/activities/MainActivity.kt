package com.example.educationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.educationapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actionBar= supportActionBar
        actionBar?.hide()
    }
}