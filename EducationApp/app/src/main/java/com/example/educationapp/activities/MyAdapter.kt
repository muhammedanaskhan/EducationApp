package com.example.educationapp.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.R
import org.w3c.dom.Text

class MyAdapter(private val notesList : ArrayList<Note>): RecyclerView.Adapter<MyAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent).inflate()

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val pdfName : Text = itemView.findViewById(R.id.tvPdfName)

    }

}