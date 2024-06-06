package com.example.campus_connect_app.latestchatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.campus_connect_app.R
import com.example.campus_connect_app.chatbot.chatting.ChatRVAdapter
import com.example.campus_connect_app.model.latestchat.MyMsg

class MyChatAdapter( val msgList : ArrayList<MyMsg>)  : RecyclerView.Adapter<ViewHolder>(){
    private lateinit var mListener : OnItemClickListener




    interface OnItemClickListener{
        fun onItemClick(question : String, type : String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder{
        val view : View
        var viewHolder : ViewHolder? = null

        when(viewType){
            0 ->{
                view = LayoutInflater.from(parent.context).inflate(com.example.campus_connect_app.R.layout.user_msg, parent, false)
                viewHolder = UserViewHolder(view, mListener)
                return viewHolder
            }

            1 ->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.bot_msg, parent, false)
                viewHolder = BotViewHolder(view, mListener)
                return  viewHolder
            }
        }

        return viewHolder!!
    }
//        MyChatVHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false), mListener)

    override fun getItemCount() = msgList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMsg = msgList[position]

        when(currentMsg.type){
            "user" -> (holder as UserViewHolder).userTv.text = currentMsg.question
            "bot" -> (holder as BotViewHolder).botTv.text = currentMsg.question
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(msgList[position].type){
            "user" -> 0
            "bot" -> 1
            else ->1
        }


    }

    inner class UserViewHolder(itemView: View, listener: OnItemClickListener) : ViewHolder(itemView){
        val userTv : TextView

        init {
            userTv = itemView.findViewById(R.id.idTVUser)

            itemView.setOnClickListener {
                msgList[position].question?.let { it1 -> listener.onItemClick(it1, msgList[position].type) }
            }
        }
    }

    inner class BotViewHolder(itemView: View, listener: OnItemClickListener) : ViewHolder(itemView){
        var botTv : TextView

        init {
            botTv = itemView.findViewById(R.id.idTVBot)

            itemView.setOnClickListener {
                msgList[position].question?.let { it1 -> listener.onItemClick(it1, msgList[position].type) }
            }
        }
    }

    inner class MyChatVHolder(itemView: View, listener: OnItemClickListener) : ViewHolder(itemView){
        val ans = itemView.findViewById<TextView>(R.id.answerText)

        init {
            itemView.setOnClickListener {
                msgList[position].question?.let { it1 -> msgList[position].type?.let { it2 ->
                    listener.onItemClick(it1,
                        it2
                    )
                } }
            }
        }
    }

    public fun clearData(){
        msgList.clear()
        notifyDataSetChanged()
    }

}