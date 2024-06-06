package com.example.campus_connect_app.placement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.campus_connect_app.R
import com.example.campus_connect_app.model.placement.CampusDrive

class DrivesListAdapter(private val context : Context, private val drivesList : ArrayList<CampusDrive>) : RecyclerView.Adapter<DrivesListAdapter.DrivesListVHolder>(){

    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position : Int, campusDrive: CampusDrive)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DrivesListVHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_drive, parent, false), mListener)

    override fun getItemCount() = drivesList.size

    override fun onBindViewHolder(holder: DrivesListVHolder, position: Int) {
        val currentDrive = drivesList[position]

        holder.role.text = currentDrive.role
        holder.companyName.text = currentDrive.companyName
        holder.location.text = currentDrive.location
        holder.fromDate.text = currentDrive.fromDate
        holder.jobType.text = currentDrive.jobType

    }

    inner class DrivesListVHolder(itemView: View, listener: OnItemClickListener) : ViewHolder(itemView){
        val role = itemView.findViewById<TextView>(R.id.tvRole)
        val companyName = itemView.findViewById<TextView>(R.id.tvCompanyName)
        val location = itemView.findViewById<TextView>(R.id.tvLocation)
        val fromDate = itemView.findViewById<TextView>(R.id.tvFromDate)
        val jobType = itemView.findViewById<TextView>(R.id.tvJobType)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition, drivesList[position])
            }
        }
}
}