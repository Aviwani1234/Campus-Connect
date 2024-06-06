package com.example.campus_connect_app

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.campus_connect_app.model.AttendanceRecord
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CompoundBarcodeView
import java.security.AccessController.getContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date


class QRActivity : AppCompatActivity() {
    private lateinit var barcodeView: CompoundBarcodeView

    private val CAMERA_PERMISSION_CODE = 100


    val attendanceList = mutableListOf<AttendanceRecord>()

    var dayOfWeek = ""
    var formattedTime = ""
    var formattedDate = ""


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        enableEdgeToEdge()
        setContentView(R.layout.activity_qractivity)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        barcodeView = findViewById(R.id.barcode_scanner)

        checkPermissions()

        val calendar = Calendar.getInstance()
        val currentDateTime = calendar.time

        val timeFormatter = SimpleDateFormat("HH:mm:ss")
         formattedTime = timeFormatter.format(currentDateTime)

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd")

// Format the date
         formattedDate = dateFormatter.format(currentDateTime)


// Define the desired day of the week format
        val dayOfWeekFormatter = SimpleDateFormat("EEEE")

// Format the day of the week
         dayOfWeek = dayOfWeekFormatter.format(currentDateTime)

        Log.d("dateTime", ""+dayOfWeek + ", "+formattedTime + ", "+formattedDate)

       /* val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permissionCheckResults = permissions.map {
            ContextCompat.checkSelfPermission(this, it)
        }

        if (permissionCheckResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            startScanning()
        } else {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                PERMISSION_REQUEST_CODE
            )
        }*/

    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            startScanning()
        }
    }


    private fun startScanning() {
         var successAlertShown = false
        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    Log.d("ScanQRActivity", "Scanned result: ${it.text}")
                    if (it.text == "Campus_Connect_Attendance") {
                        if (successAlertShown == false) {
                            addAttendanceRecord(it.text)



                            showSuccessAlert()
                            successAlertShown = true
                        }

                        // Write attendance to Excel after each scan if needed
//                        writeAttendanceToExcel()
                    }
                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
                Log.d("ScanQRActivitypoints", resultPoints.toString())
            }

        })
    }

    private fun showSuccessAlert() {
        val builder1 = AlertDialog.Builder(this)
        builder1.setTitle("Attendance")
        builder1.setMessage("Attendance submitted successfully!")
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

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }




    fun addAttendanceRecord(qrCode: String) {
        val attendanceRecord = AttendanceRecord(qrCode, Date())
        attendanceList.add(attendanceRecord)

        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.edit()

        isLoggedIn.putString("attendanceDate", formattedDate);
        isLoggedIn.putString("attendanceTime", formattedTime)
        isLoggedIn.putString("attendanceDay", dayOfWeek)
        isLoggedIn.commit();
    }

/*
    fun writeAttendanceToExcel() {
        // Create a new workbook
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Attendance")

        // Write headers
        val headerRow = sheet.createRow(0)
        headerRow.createCell(0).setCellValue("QR Code")
        headerRow.createCell(1).setCellValue("Timestamp")

        // Write attendance records
        var rowNum = 1
        for (record in attendanceList) {
            val row = sheet.createRow(rowNum++)
            row.createCell(0).setCellValue(record.qrCode)
            row.createCell(1).setCellValue(record.timestamp.toString())
        }

        // Write workbook to file
        val file = File(getExternalFilesDir(null), "Attendance.xlsx")
        val fileOut = FileOutputStream(file)
        workbook.write(fileOut)
        fileOut.close()
        workbook.close()
    }
*/

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with using the camera
                // Implement your camera functionality here
                startScanning()
            } else {
                // Permission denied, handle accordingly (e.g., show a message or disable camera functionality)
                Toast.makeText(this, "Please grant camera permission", Toast.LENGTH_SHORT).show()
            }
        }
    }
}