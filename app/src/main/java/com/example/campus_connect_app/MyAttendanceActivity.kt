package com.example.campus_connect_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campus_connect_app.databinding.ActivityMyAttendanceBinding

class MyAttendanceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyAttendanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAttendanceBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val isLoggedIn = sh.getString("isLoggedIn", "")
        val role = sh.getString("role", "")
        val name = sh.getString("name", "")
        Log.d("name", name.toString())

        val attendanceDate = sh.getString("attendanceDate", "")
        val attendanceTime = sh.getString("attendanceTime", "")
        val attendanceDay = sh.getString("attendanceDay", "")

        Log.d("dateTimeIs", ""+attendanceDate + ", "+attendanceTime + ", "+attendanceDay)
        if(attendanceDate == "" && attendanceDay == "" && attendanceTime == ""){
            binding.attendanceList.visibility = View.GONE
            binding.noAttendance.visibility = View.VISIBLE
        }

        if ( role == "teacher"){
            binding.attendanceHeader.text = "Attendance"


            binding.name.text = name
        }
        binding.name.text = name


        binding.date.text = attendanceDate
        binding.day.text = attendanceDay +"\n" +attendanceTime


    }
}