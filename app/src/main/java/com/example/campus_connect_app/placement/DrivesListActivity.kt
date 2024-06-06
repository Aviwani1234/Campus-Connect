package com.example.campus_connect_app.placement

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campus_connect_app.R
import com.example.campus_connect_app.databinding.ActivityDrivesListBinding
import com.example.campus_connect_app.model.Complaint
import com.example.campus_connect_app.model.placement.CampusDrive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DrivesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrivesListBinding
    var mList: ArrayList<CampusDrive> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrivesListBinding.inflate(layoutInflater)

//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        mList = ArrayList()

        getAllDrives()
    }

    private fun getAllDrives() {
        val dbHelper = DatabaseHelper(applicationContext)
        val drives = dbHelper.getAllDrives()
        mList.clear()

        mList.addAll(drives)

        Log.d("drives", mList.toString())

        val drivesListAdapter = DrivesListAdapter(this, mList)
        val lManager = LinearLayoutManager(this)

        if(mList.size == 0){
            binding.noDrives.visibility = View.VISIBLE
        }

        binding.rvDrivesList.adapter = drivesListAdapter
        binding.rvDrivesList.layoutManager = lManager

        drivesListAdapter.setOnItemClickListener(object : DrivesListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, campusDrive: CampusDrive) {
                val intent = Intent(this@DrivesListActivity, CompanyDetailsActivity::class.java)
                intent.putExtra("campusDrive", campusDrive)
                startActivity(intent)
            }
        })
    }
}