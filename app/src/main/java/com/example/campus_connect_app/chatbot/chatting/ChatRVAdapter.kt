package com.example.campus_connect_app.chatbot.chatting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.campus_connect_app.R
import com.example.campus_connect_app.model.chatting.MessageModal


class ChatRVAdapter(messageModalArrayList: ArrayList<MessageModal>, context: Context) :
    RecyclerView.Adapter<ViewHolder>() {
    // variable for our array list and context.
    private val messageModalArrayList: ArrayList<MessageModal>
    private val context: Context

    // constructor class.
    init {
        this.messageModalArrayList = messageModalArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        var viewHolder : ViewHolder? = null

        when (viewType) {
            0 -> {
                // below line we are inflating user message layout.
                view = LayoutInflater.from(parent.context).inflate(com.example.campus_connect_app.R.layout.user_msg, parent, false)
                 viewHolder = UserViewHolder(view)
                return viewHolder
            }

            1 -> {
                // below line we are inflating bot message layout.
                view = LayoutInflater.from(parent.context).inflate(com.example.campus_connect_app.R.layout.bot_msg, parent, false)
                viewHolder = BotViewHolder(view)
                return viewHolder
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // this method is use to set data to our layout file.
        val modal: MessageModal = messageModalArrayList[position]
        when (modal.sender) {
            "user" ->                // below line is to set the text to our text view of user layout
                (holder as UserViewHolder).userTV.setText(modal.message)

            "bot" ->                // below line is to set the text to our text view of bot layout
                (holder as BotViewHolder).botTV.setText(modal.message)
        }
    }

    override fun getItemCount(): Int {
        // return the size of array list
        return messageModalArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        // below line of code is to set position.
        return when (messageModalArrayList[position].sender) {
            "user" -> 0
            "bot" -> 1
            else -> -1
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable
        // for our text view.
        var userTV: TextView

        init {
            // initializing with id.
            userTV = itemView.findViewById<TextView>(com.example.campus_connect_app.R.id.idTVUser)
        }
    }

    class BotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable
        // for our text view.
        var botTV: TextView

        init {
            // initializing with id.
            botTV = itemView.findViewById<TextView>(R.id.idTVBot)
        }
    }
}

