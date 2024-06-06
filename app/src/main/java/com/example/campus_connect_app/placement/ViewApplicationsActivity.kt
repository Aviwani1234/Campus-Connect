package com.example.campus_connect_app.placement

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campus_connect_app.databinding.ActivityViewApplicationsBinding
import com.example.campus_connect_app.model.placement.AppliedStudents
import com.example.campus_connect_app.model.placement.CampusDrive

class ViewApplicationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewApplicationsBinding
    var mList: ArrayList<AppliedStudents> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewApplicationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_view_applications)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        mList = ArrayList()

        getAllApplications()
    }

    private fun getAllApplications() {
        val dbHelper = DatabaseHelper(applicationContext)
        val appliedStudents = dbHelper.getAllAppliedStudents()
        mList.clear()

        val application1 = AppliedStudents(10,20, "Shubham Pawar", "10/05/2000", "Pimpri Chinchvad, Pune", "9878676534", "shubhamp12@gmail.com", "Male", "B.Sc. Computer Science", "D.Y. Patil, Akurdi","Pune University","2022", "60", null )
        mList.add(application1)
        mList.addAll(appliedStudents)


        Log.d("appliedStudents", mList.toString())

        if(mList.size ==0){
            binding.listLayout.visibility = View.GONE
            binding.noApplications.visibility = View.VISIBLE
        }else{
            binding.listLayout.visibility = View.VISIBLE
            binding.noApplications.visibility = View.GONE
        }

        val appliedStudentsAdapter = ApplicationsListAdapter(this, mList)
        val lManager = LinearLayoutManager(this)

        if(mList.size == 0){
            binding.noApplications.visibility = View.VISIBLE
            binding.formsList.visibility = View.GONE
        }

        binding.rvApplications.adapter = appliedStudentsAdapter
        binding.rvApplications.layoutManager = lManager

//        appliedStudentsAdapter.setOnItemClickListener(object : ApplicationsListAdapter.OnItemClickListener{
//            override fun onItemClick(position: Int, campusDrive: CampusDrive) {
//                val intent = Intent(this@DrivesListActivity, CompanyDetailsActivity::class.java)
//                intent.putExtra("campusDrive", campusDrive)
//                startActivity(intent)
//            }
//        })

        appliedStudentsAdapter.setOnItemClickListener(object : ApplicationsListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, application: AppliedStudents) {
                                val intent = Intent(this@ViewApplicationsActivity, PlacementActivity::class.java)
                intent.putExtra("application", application)
                startActivity(intent)
            }

        })
    }
}