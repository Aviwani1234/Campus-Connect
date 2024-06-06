package com.example.campus_connect_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.campus_connect_app.model.Complaint


class ShowAllComplaintAdapter(mContext: Context, mList: List<Complaint>) :
    RecyclerView.Adapter<ShowAllComplaintAdapter.viewholder>() {
    private val mContext: Context
    private var mList: List<Complaint> = ArrayList()

    init {
        this.mContext = mContext
        this.mList = mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view: View =
            LayoutInflater.from(mContext).inflate(com.example.campus_connect_app.R.layout.comlaint_list_item, parent, false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val complaint = mList[position]

        if (complaint.image != null){
            Glide.with(mContext).load(complaint.image).into(holder.iv_complaintImage)
        }else{
            Glide.with(mContext).load(R.drawable.add_image).into(holder.iv_complaintImage)
        }
        holder.title.text = complaint.title
        holder.userName.text = complaint.userName
        holder.tv_description.text =complaint.description
        holder.tv_status.text =  complaint.status
        holder.tv_reply.text =  complaint.reply    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_complaintImage: ImageView
        var title: TextView
        var tv_description: TextView
        var tv_status: TextView
        var tv_reply: TextView
        var userName : TextView

        init {
            iv_complaintImage = itemView.findViewById(R.id.iv_complaintImage)
            title = itemView.findViewById(R.id.title)
            userName = itemView.findViewById(R.id.userName)
            tv_description = itemView.findViewById(R.id.tv_complaintDescription)
            tv_status = itemView.findViewById(R.id.tv_complaintStatus)
            tv_reply = itemView.findViewById(R.id.tv_complaintReply)
        }


    }
}

