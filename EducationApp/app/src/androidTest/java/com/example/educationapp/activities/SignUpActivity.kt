package com.example.educationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.educationapp.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        window.statusBarColor = this.resources.getColor(R.color.colorMain)

    }
}



