package com.example.campus_connect_app.chatbot.chatting

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.campus_connect_app.R
import com.example.campus_connect_app.databinding.ActivityChattingBinding
import com.example.campus_connect_app.model.chatting.MessageModal
import org.json.JSONException
import org.json.JSONObject


class ChattingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChattingBinding

    private var chatsRV: RecyclerView? = null
    private var sendMsgIB: ImageButton? = null
    private var userMsgEdt: EditText? = null
    private val USER_KEY = "user"
    private val BOT_KEY = "bot"

    // creating a variable for
    // our volley request queue.
    private var mRequestQueue: RequestQueue? = null

    // creating a variable for array list and adapter class.
    private var messageModalArrayList: ArrayList<MessageModal>? = null
    private var chatRVAdapter: ChatRVAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)

//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        chatsRV = findViewById<RecyclerView>(R.id.idRVChats)
        sendMsgIB = findViewById<ImageButton>(R.id.idIBSend)
        userMsgEdt = findViewById<EditText>(R.id.idEdtMessage)

        // below line is to initialize our request queue.

        // below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(this@ChattingActivity)
        mRequestQueue?.getCache()?.clear()

        // creating a new array list

        // creating a new array list
        messageModalArrayList = ArrayList()


        binding.idIBSend.setOnClickListener {
            if (binding.idEdtMessage.getText().toString().isEmpty()) {
                // if the edit text is empty display a toast message.
                Toast.makeText(
                    this@ChattingActivity,
                    "Please enter your message..",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // calling a method to send message
            // to our bot to get response.
            sendMessage(binding.idEdtMessage.getText().toString())

            // below line we are setting text in our edit text as empty
            binding.idEdtMessage.setText("")
        }


               chatRVAdapter = ChatRVAdapter(messageModalArrayList!!, this)

        // below line we are creating a variable for our linear layout manager.

        // below line we are creating a variable for our linear layout manager.
        val linearLayoutManager =
            LinearLayoutManager(this@ChattingActivity, RecyclerView.VERTICAL, false)

        binding.idRVChats.setLayoutManager(linearLayoutManager)

        binding.idRVChats.setAdapter(chatRVAdapter)

    }

    private fun sendMessage(userMsg: String) {
        messageModalArrayList!!.add(MessageModal(userMsg, USER_KEY))
        chatRVAdapter?.notifyDataSetChanged()

        val url = "http://api.brainshop.ai/get?bid=181456&key=U6dSoo1hTZga5Lbs&uid=[uid]&msg=$userMsg"

        // creating a variable for our request queue.
        val queue = Volley.newRequestQueue(this@ChattingActivity)


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            {response ->
                try {
                    val botResponse = response.getString("cnt")
                    messageModalArrayList!!.add(
                        MessageModal(
                            botResponse,
                            BOT_KEY
                        )
                    )
                    chatRVAdapter!!.notifyDataSetChanged()
                }catch (e : JSONException){
                    e.printStackTrace()
                    messageModalArrayList!!.add(MessageModal("No response", BOT_KEY))
//                        chatRVAdapter.notifyDataSetChanged()
                }
            }){
            messageModalArrayList!!.add(MessageModal("Sorry no response found", BOT_KEY))
//                    Toast.makeText(
//                        this@ChattingActivity,
//                        "No response from the bot..",
//                        Toast.LENGTH_SHORT
//                    ).show()
        }

        // at last adding json object
        // request to our queue.
        queue.add(jsonObjectRequest)
    }
}