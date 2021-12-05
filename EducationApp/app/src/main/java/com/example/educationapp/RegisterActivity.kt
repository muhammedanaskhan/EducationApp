package com.example.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val image_view = findViewById(R.id.go_back_arrow) as ImageView
        image_view.setOnClickListener {
            finish()
        }

        val login = findViewById(R.id.login) as TextView
        login.setOnClickListener {
            finish()
        }

    }
}