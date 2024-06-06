package com.example.campus_connect_app.placement

import DatabaseHelper
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campus_connect_app.ComplaintActivity
import com.example.campus_connect_app.Home
import com.example.campus_connect_app.R
import com.example.campus_connect_app.databinding.ActivityPlacementBinding
import com.example.campus_connect_app.model.placement.AppliedStudents
import java.util.Calendar
import java.util.Calendar.*

class PlacementActivity : AppCompatActivity() {
    private var pdfName: String? = null
    private lateinit var binding: ActivityPlacementBinding
    private lateinit var sharedPreferences: SharedPreferences

    private val PICK_PDF_FILE = 2
    private val PERMISSION_REQUEST_CODE = 1001
    var pdfByteArray : ByteArray? = null


    var studentId: Int = -1
    var driveId = 0
    var campusDriveId: String? = null
    var fullName: String? = null
    var dob: String? = null
    var address: String? = null
    var phone: String? = null
    var email: String? = null
    var gender: String? = null
    var course: String? = null
    var institute: String? = null
    var university: String? = null
    var yearOfPassing: String? = null
    var percentage: String? = null

    var studDetails : AppliedStudents ? = null

    var role = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacementBinding.inflate(layoutInflater)

        driveId = intent.getIntExtra("driveId", 0)

        studDetails = intent.getParcelableExtra<AppliedStudents>("application")
        Log.d("studDetails : ", studDetails.toString())
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        if (sharedPreferences.getString("role", "") == "teacher") {
            disableEditTexts()
        }

        studDetails?.let {
            Log.d("appliedStud", studDetails.toString());
        }

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getString("isLoggedIn", "")

        if (sharedPreferences.getString("role", "") == "teacher") {
//            binding.scrollview.visibility = View.GONE
//            binding.formsList.visibility = View.VISIBLE
            showStudentDetails()
        } else {
            binding.scrollview.visibility = View.VISIBLE
//            binding.formsList.visibility = View.GONE
        }

        if (sharedPreferences.getString("role", "") == "teacher"){
            binding.submit.visibility = View.GONE
            binding.dob.isFocusable = false
            binding.dob.isClickable = false
            binding.selectPdfLayout.visibility = View.GONE
            binding.viewPdf.visibility = View.VISIBLE


            binding.viewPdf.setOnClickListener {
                val intent = Intent(this@PlacementActivity, PDFViewActivity::class.java)
                intent.putExtra("studDetails",studDetails )
                startActivity(intent)
            }
        }else{
            binding.submit.visibility = View.VISIBLE
            binding.selectPdfLayout.visibility = View.VISIBLE
            binding.viewPdf.visibility = View.GONE

            binding.submit.setOnClickListener {
                validateForm()
            }

            binding.dob.setOnClickListener {
                showDatePickerDialog(binding.dob)
            }
        }

        binding.selectFile.setOnClickListener {
            pickPdfFile()
        }


    }

    private fun pickPdfFile() {
        checkAndRequestPermissions()
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
        }
        startActivityForResult(intent, PICK_PDF_FILE)
    }

    private fun disableEditTexts() {
        binding.fullName.isFocusable = false
        binding.fullName.isClickable = false

        binding.dob.isFocusable = false
        binding.dob.isClickable = false

        binding.address.isFocusable = false
        binding.address.isClickable = false

        binding.mobile.isFocusable = false
        binding.mobile.isClickable = false

        binding.email.isFocusable = false
        binding.email.isClickable = false

        binding.male.isFocusable = false
        binding.male.isClickable = false

        binding.female.isFocusable = false
        binding.female.isClickable = false

        binding.course.isFocusable = false
        binding.course.isClickable = false

        binding.institute.isFocusable = false
        binding.institute.isClickable = false

        binding.university.isFocusable = false
        binding.university.isClickable = false

        binding.yearOfPassing.isFocusable = false
        binding.yearOfPassing.isClickable = false

        binding.percentage.isFocusable = false
        binding.percentage.isClickable = false
    }

    private fun showStudentDetails() {
        binding.fullName.setText(studDetails?.fullName.toString())
        binding.dob.setText(studDetails?.dob.toString())
        binding.address.setText(studDetails?.address.toString())
        binding.mobile.setText(studDetails?.phone.toString())
        binding.email.setText(studDetails?.email.toString())
//        binding.gender.setText(studDetails?.fullName.toString())

        if(studDetails?.gender == "Male"){
            binding.male.isChecked = true
            binding.female.isChecked = false
        }else{

            binding.male.isChecked = false
            binding.female.isChecked = true
        }
        binding.course .setText(studDetails?.course.toString())
        binding.institute.setText(studDetails?.institute.toString())
        binding.university.setText(studDetails?.university.toString())
        binding.yearOfPassing.setText(studDetails?.yearOfPassing.toString())
        binding.percentage.setText(studDetails?.percentage.toString())
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = getInstance()
        val year = calendar.get(YEAR)
        val month = calendar.get(MONTH)
        val day = calendar.get(DAY_OF_MONTH)
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

    private fun validateForm() {
        if (binding.fullName.text.toString().isNullOrBlank()) {
            binding.fullName.error = "Enter Name"
            binding.fullName.requestFocus()
            return
        }
        if (binding.dob.text.toString().isNullOrBlank()) {
            binding.dob.error = "Enter DOB"
            binding.dob.requestFocus()
            return
        }
        if (binding.address.text.toString().isNullOrBlank()) {
            binding.address.error = "Enter Address"
            binding.address.requestFocus()
            return
        }
        if (binding.mobile.text.toString().isNullOrBlank()) {
            binding.mobile.error = "Enter Mobile Number"
            binding.mobile.requestFocus()
            return
        }
        if (binding.email.text.toString().isNullOrBlank()) {
            binding.email.error = "Enter Email"
            binding.email.requestFocus()
            return
        }
        if (!binding.male.isChecked && !binding.female.isChecked) {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show()
            binding.genderRadioGroup.requestFocus()
            return
        } else {
            fullName = binding.fullName.text.toString()
            dob = binding.dob.text.toString()
            address = binding.address.text.toString()
            phone = binding.mobile.text.toString()
            email = binding.email.text.toString()
            val genderId = binding.genderRadioGroup.checkedRadioButtonId
            if (genderId == 1) {
                gender = "Male"
            } else {
                gender = "Female"
            }
            course = binding.course.text.toString()
            institute = binding.institute.text.toString()
            university = binding.university.text.toString()
            yearOfPassing = binding.yearOfPassing.text.toString()
            percentage = binding.percentage.text.toString()

            val appliedStudent = AppliedStudents(
                1,
                driveId,
                fullName,
                dob,
                address,
                phone,
                email,
                gender,
                course,
                institute,
                university,
                yearOfPassing,
                percentage,
                pdfByteArray
            )

            Log.d("appliedStudent is", appliedStudent.toString())

            val dbHelper = DatabaseHelper(applicationContext)
            try {
                val inserted = dbHelper.insertAppliedStudent(appliedStudent)
                if (inserted){
                    showSuccessAlert()
                }
            }catch (e : SQLiteException){
                e.printStackTrace()
            }
        }

    }

    private fun showSuccessAlert() {
        val builder1 = AlertDialog.Builder(this)
        builder1.setTitle("Submitted")
        builder1.setMessage("Placement form submitted successfully!")
        builder1.setCancelable(false)

        builder1.setPositiveButton(
            "Ok"
        ) { dialog, id ->
            dialog.cancel()
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }


        val alert11 = builder1.create()
        alert11.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PDF_FILE && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                 pdfByteArray = readBytesFromUri(uri)
                pdfName = getFileName(uri)
                // Now you can use the pdfByteArray to create a Complaint object and insert it into the database
                Log.d("pdfFile : ", pdfByteArray?.size.toString());
                binding.pdfName.text = pdfName
            }
        }
    }

    private fun getFileName(uri: Uri): String {
        var name = ""
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                name = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        }
        return name
    }

    private fun readBytesFromUri(uri: Uri): ByteArray {
        val inputStream = contentResolver.openInputStream(uri)
        return inputStream?.readBytes() ?: ByteArray(0)
    }

    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        } else {
            openFilePicker()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFilePicker()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


}