package com.example.campus_connect_app

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.campus_connect_app.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edemail = binding.edemail
        val edpassword = binding.edpassword
        val btnforgot = binding.txtpassword
        val btnlogin = binding.btnlogin

        val btnregsiter = binding.btnregister

        btnregsiter.setOnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
        }

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val isLoggedIn = sh.getString("isLoggedIn", "")

        if (isLoggedIn == "true") {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        auth = FirebaseAuth.getInstance()

        btnlogin.setOnClickListener {

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edemail.text.toString()).matches()) {
                edemail.setError("Enter Email Id")
                return@setOnClickListener
            } else if (edpassword.text.isEmpty()) {
                edpassword.setError("Enter Password")
                return@setOnClickListener
            } else if (edemail.text.toString() == "rithomkar097@gmail.com" && edpassword.text.toString() == "Ritika123"
            ) {
                val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                val isLoggedIn = sharedPreferences.edit()

                isLoggedIn.putString("isLoggedIn", "true");
                isLoggedIn.putString("role", "student")
                isLoggedIn.putString("name", "Riitka Homkar")
                isLoggedIn.commit();


                val intent = Intent(applicationContext, Home::class.java)
                startActivity(intent)
                finish()
            }
            else if (edemail.text.toString() == "Pratikgagare@gmail.com" && edpassword.text.toString() == "Pratik112233"
            ) {
                val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                val isLoggedIn = sharedPreferences.edit()

                isLoggedIn.putString("isLoggedIn", "true");
                isLoggedIn.putString("role", "student")
                isLoggedIn.putString("name", "Pratik Gagare")
                isLoggedIn.commit();


                val intent = Intent(applicationContext, Home::class.java)
                startActivity(intent)
                finish()
            }
            else if (edemail.text.toString() == "Avinashwani@gmail.com" && edpassword.text.toString() == "Avinash456"
            ) {
                val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                val isLoggedIn = sharedPreferences.edit()

                isLoggedIn.putString("isLoggedIn", "true");
                isLoggedIn.putString("role", "student")
                isLoggedIn.putString("name", "Avinash Wani")
                isLoggedIn.commit();


                val intent = Intent(applicationContext, Home::class.java)
                startActivity(intent)
                finish()
            }else if (edemail.text.toString() == "teacher@gmail.com" && edpassword.text.toString() == "Teacher@123") {
                val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                val isLoggedIn = sharedPreferences.edit()

                isLoggedIn.putString("isLoggedIn", "true");
                isLoggedIn.putString("role", "teacher")
                isLoggedIn.commit();

                val intent = Intent(applicationContext, Home::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Please enter correct email & password!", Toast.LENGTH_SHORT).show()
            }


            /*auth.signInWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"successfully Login", Toast.LENGTH_LONG).show()

                        val intent = Intent(applicationContext,Home::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Failed to login", Toast.LENGTH_LONG).show()
                        Log.d("signIn", it.exception.toString())
                    }
                }*/
        }

        btnforgot.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dailog_forgot, null)
            val username = view.findViewById<EditText>(R.id.ed_forgot)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(username)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ -> })
            builder.show()

        }
    }

    private fun forgotpassword(username: EditText) {

        auth.sendPasswordResetEmail(username!!.text.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Email Sent", Toast.LENGTH_LONG).show()

                } else {
                    Log.d("forgotPassword", it.exception.toString())

                }
            }


    }
}