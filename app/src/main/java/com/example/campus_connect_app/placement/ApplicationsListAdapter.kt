package com.example.campus_connect_app.placement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.campus_connect_app.R
import com.example.campus_connect_app.model.placement.AppliedStudents
import com.example.campus_connect_app.model.placement.CampusDrive

class ApplicationsListAdapter(private val context : Context, private val applications : ArrayList<AppliedStudents>) : RecyclerView.Adapter<ApplicationsListAdapter.ApplicationsViewHolder>(){

    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position : Int, application: AppliedStudents)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ApplicationsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student_details, parent, false), mListener)

    override fun getItemCount() = applications.size

    override fun onBindViewHolder(holder: ApplicationsViewHolder, position: Int) {
        val currentApplication = applications[position]

        holder.studentName.text = currentApplication.fullName


    }

    inner class ApplicationsViewHolder(itemView: View, listener: OnItemClickListener) : ViewHolder(itemView){
        val studentName = itemView.findViewById<TextView>(R.id.studentName)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition, applications[position])
            }
        }
}
}