package com.example.campus_connect_app.placement

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campus_connect_app.R
import com.example.campus_connect_app.databinding.ActivityCompanyDetailsBinding
import com.example.campus_connect_app.model.placement.CampusDrive

class CompanyDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCompanyDetailsBinding
    var campusDrive : CampusDrive? = null
    private lateinit var sharedPreferences: SharedPreferences
    var role : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyDetailsBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)
         campusDrive = intent.getParcelableExtra<CampusDrive>("campusDrive")
        Log.d("campusDrive Intent : ", campusDrive.toString())
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        role = sharedPreferences.getString("role", "")

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        showCompanyDetails()
        binding.apply.setOnClickListener {
            val intent = Intent(this@CompanyDetailsActivity, PlacementActivity::class.java)
            intent.putExtra("driveId", campusDrive!!.driveId)
            startActivity(intent)
        }
    }

    private fun showCompanyDetails() {
        binding.role.text = campusDrive?.role
        binding.companyName.text = campusDrive?.companyName
        binding.experience.text = campusDrive?.experience
        binding.hiringCount.text = campusDrive?.hiringCount
        binding.location.text = campusDrive?.location
        binding.salary.text = campusDrive?.salary
        binding.requirements.text = campusDrive?.requirements
        binding.description.text = campusDrive?.companyDescription
        binding.requirementsEdu.text = campusDrive?.requirementsEdu

        if (role == "teacher"){
            binding.apply.visibility = View.GONE
            binding.viewApplications.visibility = View.VISIBLE

            binding.viewApplications.setOnClickListener {
                val intent = Intent(this@CompanyDetailsActivity, ViewApplicationsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}