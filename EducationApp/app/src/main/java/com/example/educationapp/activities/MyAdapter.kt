package com.example.educationapp.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.R
import org.w3c.dom.Text

class MyAdapter(private val notesList : ArrayList<Note>): RecyclerView.Adapter<MyAdapter.myViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position : Int)

}

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notes_item,parent,false)
        return myViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val noteItem = notesList[position]
        holder.pdfName.text = noteItem.Name
        holder.pdfLink.text = noteItem.Link

    }

    override fun getItemCount(): Int {

        return notesList.size

    }

    class myViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val pdfName : TextView = itemView.findViewById(R.id.tvPdfName)
        val pdfLink : TextView = itemView.findViewById(R.id.tvPdfLink)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

}