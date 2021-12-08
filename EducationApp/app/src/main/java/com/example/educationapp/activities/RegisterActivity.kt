package com.example.educationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.example.educationapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.statusBarColor = this.resources.getColor(R.color.colorMain)

        val goBack = findViewById(R.id.signup) as TextView

        goBack.setOnClickListener {
            finish()
        }

        signUp_buttom.setOnClickListener {
            registerUser()
        }

    }


    private fun registerUser(){
        val name:String = et_name.text.toString().trim { it <= ' ' }
        val email:String = et_email.text.toString().trim { it <= ' ' }
        val password:String = et_password.text.toString()
        val confpassword:String = conf_password.text.toString()

        if (validateForm(name,email,password,confpassword)){

            showProgressDialog("Please wait")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                hideProgressDialog()
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    Toast.makeText(this, "You have successfully registered!", Toast.LENGTH_LONG)
                        .show()

                    FirebaseAuth.getInstance().signOut()
                    finish()
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun  validateForm(name: String, email: String, password: String, confPassword: String): Boolean{
        return when{
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter a name")
                false
            }
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
            TextUtils.isEmpty(confPassword) -> {
                showErrorSnackBar("Please re-enter password")
                false
            }
            (confPassword != password) -> {
                showErrorSnackBar("Passwords doesn't match")
                false
            }
            (password.contains(" ") && confPassword.contains(" ")) -> {
                showErrorSnackBar("Space not allowed in password")
                false
            }
            else -> {
                return true
            }
        }
    }

}