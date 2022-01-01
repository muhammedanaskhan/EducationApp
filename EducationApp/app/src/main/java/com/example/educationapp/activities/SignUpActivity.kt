package com.example.educationapp.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.educationapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.w3c.dom.Text

class SignUpActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        window.statusBarColor = this.resources.getColor(R.color.colorMain)

        if(auth.currentUser != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        login_buttom.setOnClickListener {
            signInUser()
        }

        val registerbutton = findViewById(R.id.signup) as TextView
        registerbutton.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signInUser() {

        val email: String = emailAddress.text.toString()
        val password: String = password.text.toString()

        if (validateForm(email, password)) {
            showProgressDialog("Logging in")

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        Log.d("Sign in", "signInWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        Log.w("Sign in", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }

    }

    private fun  validateForm(email: String, password: String): Boolean{
        return when{

            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter the Email ID")
                false
            }
            (email.contains(" ")) -> {
                showErrorSnackBar("Please enter the correct Email ID")
                false
            }

            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password")
                false
            }

            else -> {
                return true
            }
        }
    }



}



