package com.example.campus_connect_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.campus_connect_app.model.MessageModal


class MessageRVAdapter(
    private val messageModalArrayList: ArrayList<MessageModal>, private val context: Context
) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        var vHolder : ViewHolder? = null

        when (viewType) {
            0 -> {
                // below line we are inflating user message layout.
                view = LayoutInflater.from(parent.context).inflate(R.layout.user_msg, parent, false)
                vHolder = UserViewHolder(view)
                return vHolder
            }

            1 -> {
                // below line we are inflating bot message layout.
                view = LayoutInflater.from(parent.context).inflate(R.layout.bot_msg, parent, false)
                vHolder = BotViewHolder(view)
                return vHolder
            }
            2 -> {
                // below line we are inflating bot message layout.
                view = LayoutInflater.from(parent.context).inflate(R.layout.custom_questions_layout, parent, false)
                vHolder = BotViewHolder(view)
                return vHolder
            }
        }
        return vHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // this method is use to set data to our layout file.
        val modal = messageModalArrayList[position]
        when (modal.sender) {
            "user" ->                // below line is to set the text to our text view of user layout
                (holder as UserViewHolder).userTV.text = modal.message

            "bot" ->                // below line is to set the text to our text view of bot layout
                (holder as BotViewHolder).botTV.text = modal.message
            "custom" ->{
                val customQuestionHolder = holder as CustomQueVHolder
                customQuestionHolder.questionTextView.text = modal.question
                customQuestionHolder.option1Button.text = modal.option1
                customQuestionHolder.option2Button.text = modal.option2
                customQuestionHolder.option3Button.text = modal.option3
                customQuestionHolder.option4Button.text = modal.option4
                customQuestionHolder.option5Button.text = modal.option5
                customQuestionHolder.option6Button.text = modal.option6
            }
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
            "custom" ->2
            else -> -1
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable
        // for our text view.
        var userTV: TextView

        init {
            // initializing with id.
            userTV = itemView.findViewById(R.id.idTVUser)
        }
    }

    class BotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable
        // for our text view.
        var botTV: TextView

        init {
            // initializing with id.
            botTV = itemView.findViewById(R.id.idTVBot)
        }
    }

    class CustomQueVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable
        // for our text view.
        var questionTextView: TextView
        var option1Button: TextView
        var option2Button: TextView
        var option3Button: TextView
        var option4Button: TextView
        var option5Button: TextView
        var option6Button: TextView

        init {
            // initializing with id.
            questionTextView = itemView.findViewById(R.id.questionTextView)
            option1Button = itemView.findViewById(R.id.option1Button)
            option2Button = itemView.findViewById(R.id.option2Button)
            option3Button = itemView.findViewById(R.id.option3Button)
            option4Button = itemView.findViewById(R.id.option4Button)
            option5Button = itemView.findViewById(R.id.option5Button)
            option6Button = itemView.findViewById(R.id.option6Button)

        }
    }
}

