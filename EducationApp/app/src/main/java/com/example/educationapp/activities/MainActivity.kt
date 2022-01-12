package com.example.educationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import java.lang.Exception

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener  {


    private lateinit var database: DatabaseReference
    private lateinit var pdfRecyclerView: RecyclerView
    private lateinit var pdfArrayList: ArrayList<Note>


    val pdfRef = Firebase.storage.reference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpActionBar()

        navView.setNavigationItemSelectedListener(this)

        //val view = findViewById(R.id.tvPdfName) as TextView

        //database = Firebase.database.reference

        pdfRecyclerView = findViewById(R.id.notesList)
        pdfRecyclerView.layoutManager = LinearLayoutManager(this)
        pdfRecyclerView.setHasFixedSize(true)

        pdfArrayList = arrayListOf<Note>()


        getNoteData()


    }

    private fun getNoteData() {


        database = FirebaseDatabase.getInstance().getReference("Notes")

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for( userSnapshot in snapshot.children ){

                        val pdf = userSnapshot.getValue(Note::class.java)

                        pdfArrayList.add(pdf!!)

                    }

                    pdfRecyclerView.adapter = MyAdapter(pdfArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

    }

    private fun setUpActionBar(){
        setSupportActionBar(toolbar_main_activity)
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu)

        toolbar_main_activity.setNavigationOnClickListener {
            toggleDrawer()
        }

    }

    private fun toggleDrawer(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            doubleBackToExit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.nav_sign_out -> {

                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this,SignUpActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()

                Toast.makeText(this@MainActivity,"logout",Toast.LENGTH_SHORT).show()

            }

            R.id.nav_home -> {

                Toast.makeText(this@MainActivity,"home",Toast.LENGTH_SHORT).show()

            }


        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

//    private fun downloadImage(filename: String) = CoroutineScope(Dispatchers.IO).launch{
//
//        try {
//            val bytes = pdfRef.child("pdfs/$filename").getBytes()
//        }catch (e: Exception){
//            withContext(Dispatchers.Main){
//                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
//            }
//        }
//
//    }

}
