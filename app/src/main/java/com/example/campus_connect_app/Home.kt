package com.example.campus_connect_app

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.campus_connect_app.databinding.ActivityHomeBinding
import com.example.campus_connect_app.latestchatbot.ChatActivity
import com.example.campus_connect_app.placement.CreatePlacementActivity
import com.example.campus_connect_app.placement.DrivesListActivity

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val complaint = binding.complaint


        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getString("isLoggedIn", "")

        if (isLoggedIn == "true") {
            binding.logout.visibility = View.VISIBLE
        }

        complaint.setOnClickListener {
            val intent = Intent(this, ComplaintActivity::class.java)
            startActivity(intent)
        }

        binding.chatBot.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        binding.hostelTracking.setOnClickListener {
            val intent = Intent(this@Home, HostelTrackingActivity::class.java)
            startActivity(intent)
        }

        binding.qrAttendance.setOnClickListener {
            showOptions("QR")
        }

        binding.placement.setOnClickListener {
            if (sharedPreferences.getString("role", "") == "teacher") {
                showOptions("placement")
//                val intent = Intent(this, CreatePlacementActivity::class.java)
//                startActivity(intent)
            } else {
                val intent = Intent(this, DrivesListActivity::class.java)
                startActivity(intent)
            }
        }

        binding.logout.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("Logout?")
            builder1.setMessage("Are you sure you want to logout?")
            builder1.setCancelable(false)

            builder1.setPositiveButton(
                "Yes"
            ) { dialog, id ->
                val editor = sharedPreferences.edit()
                editor.putString("isLoggedIn", "false")
                editor.apply()
                editor.commit()
                binding.logout.visibility = View.GONE
                dialog.cancel()

                Toast.makeText(this, "Logout successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            builder1.setNegativeButton(
                "No"
            ) { dialog, id ->
                dialog.cancel()
            }


            val alert11 = builder1.create()
            alert11.show()


        }

        /* val img1 = binding.client
         val img2 = binding.owner

         img1.setOnClickListener {
             Toast.makeText(applicationContext,"Teacher", Toast.LENGTH_LONG).show()
         }
         img2.setOnClickListener {
             Toast.makeText(applicationContext,"Student",Toast.LENGTH_LONG).show()
         }*/


    }

    private fun showOptions(popupType: String) {
        val dialog = Dialog(this, R.style.Theme_Dialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val myAttendanceButton = dialog.findViewById<TextView>(R.id.check_attendance)


        dialog.setCancelable(true)
        dialog.setContentView(R.layout.qr_options_layout)

        val submit_attendance = dialog.findViewById(R.id.submit_attendance) as TextView
        val check_attendance = dialog.findViewById(R.id.check_attendance) as TextView

        val role = sharedPreferences.getString("role", "")


        if (popupType == "QR") {
            if (role == "teacher") {
                submit_attendance.visibility = View.GONE
                check_attendance.text = "Check Attendance"
            }

            submit_attendance.setOnClickListener {
                dialog.cancel()
                val intent = Intent(this, QRActivity::class.java)
                startActivity(intent)
            }
            check_attendance.setOnClickListener {
                dialog.cancel()
                val intent = Intent(this, MyAttendanceActivity::class.java)
                startActivity(intent)
            }


        } else if (popupType == "placement" && role == "teacher") {
            submit_attendance.text = "Create Campus Drive"
            check_attendance.text = "My Campus Drives"
            submit_attendance.setOnClickListener {
                dialog.cancel()
                val intent = Intent(this, CreatePlacementActivity::class.java)
                startActivity(intent)
            }
            check_attendance.setOnClickListener {
                dialog.cancel()
                val intent = Intent(this, DrivesListActivity::class.java)
                startActivity(intent)

            }
        }





        dialog.show()


    }
}