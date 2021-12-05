package com.example.educationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        window.statusBarColor = this.resources.getColor(R.color.colorMain)

    }


    fun register_activity_start(view: View) {

        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }


}