package com.example.campus_connect_app.placement

import DatabaseHelper
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campus_connect_app.ComplaintActivity
import com.example.campus_connect_app.Home
import com.example.campus_connect_app.R
import com.example.campus_connect_app.databinding.ActivityCreatePlacementBinding
import com.example.campus_connect_app.model.placement.CampusDrive
import java.util.Calendar

class CreatePlacementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePlacementBinding
    var role = ""
    var companyName = ""
    var location = ""
    var description = ""
    var fromDate = ""
    var toDate = ""
    var jobType = ""
    var hiringCount = ""
    var requirementsEdu = ""
    var requirements = ""
    var salary = ""
    var experience = ""
    var rounds = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlacementBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.submit.setOnClickListener {
            if (validateInputs()){
                addCampusDrive()
            }
        }

        binding.fromDate.setOnClickListener {
            showDatePickerDialog(binding.fromDate)
        }

        binding.toDate.setOnClickListener {
            showDatePickerDialog(binding.toDate)
        }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Formatting the selected date
                val selectedDate = "${selectedDay.toString().padStart(2, '0')}/${(selectedMonth + 1).toString().padStart(2, '0')}/$selectedYear"
                editText.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun validateInputs()  :  Boolean{
        role = binding.role.text.toString()
        companyName = binding.companyName.text.toString()
        location = binding.location.text.toString()
        description = binding.description.text.toString()
        fromDate = binding.fromDate.text.toString()
        toDate = binding.toDate.text.toString()
        jobType = binding.jobType.text.toString()
        hiringCount = binding.hiringCount.text.toString()
        requirementsEdu = binding.requirementsEdu.text.toString()
        requirements = binding.requirements.text.toString()
        salary = binding.salary.text.toString()
        experience = binding.experience.text.toString()
        rounds = binding.rounds.text.toString()


        if (companyName.isNullOrEmpty()){
            binding.companyName.error = "Enter Company Name"
            binding.companyName.requestFocus()
            return false
        }
        if(role.isNullOrEmpty()){
            binding.role.error = "Enter role"
            binding.role.requestFocus()
            return false
        }
        if (location.isNullOrEmpty()){
            binding.location.error = "Enter Location"
            binding.location.requestFocus()
            return false
        }
        if (description.isNullOrEmpty()){
            binding.description.error = "Enter Company Name"
            binding.description.requestFocus()
            return false
        }
        if (fromDate.isNullOrEmpty()){
            binding.fromDate.error = "Enter from date"
            binding.fromDate.requestFocus()
            return false
        }
        if (toDate.isNullOrEmpty()){
            binding.toDate.error = "Enter to date"
            binding.toDate.requestFocus()
            return false
        }
        if (jobType.isNullOrEmpty()){
            binding.jobType.error = "Enter job type"
            binding.jobType.requestFocus()
            return false
        }
        if (hiringCount.isNullOrEmpty()){
            binding.hiringCount.error = "Enter hiring count"
            binding.hiringCount.requestFocus()
            return false
        }
        if (requirementsEdu.isNullOrEmpty()){
            binding.requirementsEdu.error = "Enter educational requirements"
            binding.requirementsEdu.requestFocus()
            return false
        }
        if (requirements.isNullOrEmpty()){
            binding.requirements.error = "Enter requirements"
            binding.requirements.requestFocus()
            return false
        }
        if (salary.isNullOrEmpty()){
            binding.salary.error = "Enter salary"
            binding.salary.requestFocus()
            return false
        }
        if (experience.isNullOrEmpty()){
            binding.experience.error = "Enter experience"
            binding.experience.requestFocus()
            return false
        }
        if (rounds.isNullOrEmpty()){
            binding.rounds.error = "Enter no. of rounds"
            binding.rounds.requestFocus()
            return false
        } else{
            return true
        }
    }

    private fun addCampusDrive() {
        val campusDrive = CampusDrive(
            role = role,
            companyName = companyName,
            location = location,
            companyDescription = description,
            fromDate = fromDate,
            toDate = toDate,
            jobType = jobType,
            hiringCount = hiringCount,
            requirements = requirements,
            requirementsEdu = requirementsEdu,
            salary = salary,
            experience = experience,
            rounds = rounds,
        )

        val dbHelper = DatabaseHelper(applicationContext)
        try {
            val inserted = dbHelper.insertCampusDrive(campusDrive)
            if (inserted){
                showAlert(inserted)
            }
        }catch (e : SQLiteException){
            e.printStackTrace()
        }    }

    private fun showAlert(inserted : Boolean){
        val builder1 = AlertDialog.Builder(this)
        builder1.setTitle("Campus Drive")

        if (inserted){
            builder1.setMessage("Campus Drive added successfully!")
        }else{
            builder1.setMessage("Unable to add Campus Drive")
        }
        builder1.setCancelable(false)

        builder1.setPositiveButton(
            "Ok"
        ) { dialog, id ->
            dialog.cancel()
            finish()
        }


        val alert11 = builder1.create()
        alert11.show()
    }
}