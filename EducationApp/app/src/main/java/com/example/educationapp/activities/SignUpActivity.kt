package com.example.educationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import com.example.educationapp.R
import org.w3c.dom.Text

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        window.statusBarColor = this.resources.getColor(R.color.colorMain)

        val registerbutton = findViewById(R.id.signup) as TextView
        registerbutton.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }


    }






}



