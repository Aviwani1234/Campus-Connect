package com.example.campus_connect_app.chatbot

import AnswerAdapter
import ChatAdapter
import QuestionAnsAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campus_connect_app.R
import com.example.campus_connect_app.model.Answer
import com.example.campus_connect_app.model.Question

class ChatBotActivity : AppCompatActivity() {
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var answerAdapter: AnswerAdapter
    private val chatMessages: MutableList<Pair<String, String>> = mutableListOf()
    private lateinit var answersRecyclerView: RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        val chatRecyclerView: RecyclerView = findViewById(R.id.chatRecyclerView)
        val answersRecyclerView: RecyclerView = findViewById(R.id.answersRecyclerView)

        chatAdapter = ChatAdapter(this,chatMessages) { position ->
            val (message, sender) = chatMessages[position]
            Log.d("ChatAdapter", "Clicked message: $message")
            Toast.makeText(this, "Clicked message: $message", Toast.LENGTH_SHORT).show()
        }
        chatRecyclerView.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(this@ChatBotActivity)
        }

        answerAdapter = AnswerAdapter(emptyList()) { answer ->
            // Handle answer click if needed
            Toast.makeText(this, answer.toString(), Toast.LENGTH_SHORT).show()
        }

        answersRecyclerView.apply {
            adapter = answerAdapter
            layoutManager = LinearLayoutManager(this@ChatBotActivity)
        }

        // Your code for setting up questions and answers
        val questions = listOf(
            Question(
                1,
                "Choose any one of the options below : ",
                listOf(
                    Answer(1, "About College"),
                    Answer(2, "Courses"),
                    Answer(3, "Contact Details"),
                    Answer(5, "Brochure"),
                )
            )
        )

        val onItemClick: (Question, Answer) -> Unit = { question, answer ->
            showAnswerForQuestion(answer.text, answer.text)
        }

        val questionAnsAdapter = QuestionAnsAdapter(questions, onItemClick)
        chatRecyclerView.adapter = questionAnsAdapter
    }

    private fun showAnswerForQuestion(question: String, answer: String) {
        // Update chat messages
        chatMessages.add(Pair(question, "User"))
        chatMessages.add(Pair(answer, "Bot"))
        var answerToShow = ""

        when(question){
            "About College"->{
                answerToShow = "Dr. D. Y. Patil Prathishthan's, D.Y. Patil College of Engineering, was established in 1984 in Pimpri and later shifted to Akurdi complex in 2001, which is in the vicinity of Pimpri Chinchwad Industrial area, one of the biggest Industrial belts in Asia. The college spreads over 10 acres of land with seven Engineering disciplines."
            }
        }

        chatAdapter.notifyAnswer()
//        chatAdapter.notifyDataSetChanged()

        // Update AnswerAdapter with new answer
        answerAdapter.setAnswers(listOf(Answer(0, answerToShow)))
//        answerAdapter.notifyDataSetChanged()
    }
}
