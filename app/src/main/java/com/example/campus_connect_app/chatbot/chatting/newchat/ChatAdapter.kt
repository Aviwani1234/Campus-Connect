import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campus_connect_app.R
import com.example.campus_connect_app.model.Answer

class ChatAdapter(private val context: Context,
    private val chatMessages: List<Pair<String, String>>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    var answerAdapter : AnswerAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val (message, sender) = chatMessages[position]
        holder.bind(message, sender)


        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = chatMessages.size
    fun notifyAnswer() {
        answerAdapter?.notifyDataSetChanged()
    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)

        private val myCustomAnswer = itemView.findViewById<RecyclerView>(R.id.myCustomAnswer)




        @SuppressLint("SetTextI18n")
        fun bind(message: String, sender: String) {
            messageTextView.text = "$sender: $message"
            val answersList = ArrayList<Answer>()
            answersList.add(Answer(1,"test1"))
            answersList.add(Answer(2,"test2"))
            answersList.add(Answer(3,"test3"))
            answersList.add(Answer(4,"test4"))

             answerAdapter = AnswerAdapter(answersList){
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            }
            myCustomAnswer.apply {
                adapter = answerAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}

