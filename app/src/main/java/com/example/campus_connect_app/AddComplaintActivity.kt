package com.example.campus_connect_app

import DatabaseHelper
import android.Manifest
import android.app.AlertDialog
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.campus_connect_app.databinding.ActivityAddComplaintBinding
import com.example.campus_connect_app.model.Complaint
import java.io.File
import java.io.InputStream

class AddComplaintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddComplaintBinding
    private val pickImageCode = 100
    private var imageUri: Uri? = null
    private lateinit var imageToUpload: File
    private lateinit var stream: InputStream
    private var realPath: String? = null
    private val REQUEST_PERMISSION_CODE = 101



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddComplaintBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.upload.setOnClickListener {
            pickImage()
        }

        binding.submit.setOnClickListener {
            saveComplaint()
        }

    }

    private fun saveComplaint() {
        val name = binding.name.text.toString()
        val description = binding.description.text.toString()
        val department = binding.department.text.toString()


        val realPath = getRealPathFromURI(imageUri)
        if (realPath == null) {
            Log.e("Save Complaint", "Failed to get real path for image")



            val complaint = Complaint(
                userName = name,
                department = department,
                status = "Pending",
                reply = "",
//            image = imageByteArray,
                image = R.drawable.add_image,
                description = description
            )

            val dbHelper = DatabaseHelper(applicationContext)
            val inserted = dbHelper.insertComplaint(complaint)

            showSuccessAlert()
            Log.d("inserted", inserted.toString())
            return
        }else{
            val complaint = Complaint(
                userName = name,
                department = department,
                status = "Pending",
                reply = "",
//            image = imageByteArray,
                image = R.drawable.add_image,
                description = description
            )

            val dbHelper = DatabaseHelper(applicationContext)
            val inserted = dbHelper.insertComplaint(complaint)

            showSuccessAlert()
            Log.d("inserted", inserted.toString())
        }

        // Read the image file as an InputStream
        val inputStream = File(realPath).inputStream()

        // Convert the InputStream to a ByteArray
        val imageByteArray = inputStream.readBytes()

        val complaint = Complaint(
            userName = name,
            department = department,
            status = "Pending",
            reply = "",
//            image = imageByteArray,
            image = R.drawable.add_image,
            description = description
        )

        val dbHelper = DatabaseHelper(applicationContext)
        val inserted = dbHelper.insertComplaint(complaint)

        showSuccessAlert()
        Log.d("inserted", inserted.toString())


    }

    private fun showSuccessAlert() {
        val builder1 = AlertDialog.Builder(this)
        builder1.setTitle("Complaints")
        builder1.setMessage("Complaint submitted successfully!")
        builder1.setCancelable(false)

        builder1.setPositiveButton(
            "Ok"
        ) { dialog, id ->
            dialog.cancel()
            val intent = Intent(this, ComplaintActivity::class.java)
            startActivity(intent)
            finish()
        }


        val alert11 = builder1.create()
        alert11.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun pickImage() {
        Log.d("pickImage","pickImage")
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission has already been granted, perform necessary actions here
//            val gallery = Intent(
//                Intent.ACTION_PICK,
//                MediaStore.Images.Media.INTERNAL_CONTENT_URI
//            ).setType("image/*")
//
//            startActivityForResult(gallery, pickImageCode)

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }

            startActivityForResult(intent, pickImageCode)
        } else {
            // Permission has not been granted yet, request it from the user
//            requestPermissions(
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                REQUEST_PERMISSION_CODE
//            )

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted, request it
                requestStoragePermission()
            }
        }
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_PERMISSION_CODE
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with picking image
                pickImage()
            } else {
                // Permission denied, handle accordingly (e.g., show a message)
                Log.e("Permission", "Storage permission denied")
            }
        }
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImageCode) {
//            // Get the selected image URI
//            imageUri = data?.data
//
//            Glide.with(this).load(imageUri).into(binding.image)
//
//            // Convert the URI to the real path of the image file
//            realPath = getRealPathFromURI(imageUri)
//
//            // Create a File object from the real path
//            imageToUpload = File(realPath)
//
//            Log.d("imageURI", imageUri.toString())
//
//            stream = this@AddComplaintActivity.contentResolver.openInputStream(imageUri!!)!!
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != RESULT_CANCELED && resultCode != null){
            if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImageCode) {
                // Get the selected image URI
                val imageUri = data?.data

                if (imageUri != null) {
                    Glide.with(this).load(imageUri).into(binding.image)

                    // Convert the URI to the real path of the image file
                    val realPath = getRealPathFromURI(imageUri)

                    if (realPath != null) {
                        // Create a File object from the real path
                        val imageToUpload = File(realPath)

                        Log.d("imageURI", imageUri.toString())

                        // Open input stream safely
                        try {
                            val stream = contentResolver.openInputStream(imageUri)
                            // Use the stream as needed
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        Log.e("getRealPathFromURI", "Failed to get real path from URI")
                    }
                } else {
                    Log.e("onActivityResult", "Image URI is null")
                }
            }
        }
    }
//    private fun getRealPathFromURI(contentUri: Uri?): String? {
//        val projection = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor: Cursor? = contentUri?.let {
//            this@AddComplaintActivity.contentResolver.query(
//                it,
//                projection,
//                null,
//                null,
//                null
//            )
//        }
//        cursor?.use {
//            if (it.moveToFirst()) {
//                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//                return it.getString(columnIndex)
//            }
//        }
//        return null
//    }

    private fun getRealPathFromURI(contentUri: Uri?): String? {
        var realPath: String? = null
        contentUri?.let { uri ->
            // Try to handle different URI schemes
            if (DocumentsContract.isDocumentUri(this, uri)) {
                when (uri.authority) {
                    "com.android.externalstorage.documents" -> {
                        val docId = DocumentsContract.getDocumentId(uri)
                        val split = docId.split(":")
                        if (split.size == 2) {
                            val type = split[0]
                            if ("primary".equals(type, true)) {
                                realPath = Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                            }
                        }
                    }
                    "com.android.providers.downloads.documents" -> {
                        val id = DocumentsContract.getDocumentId(uri)
                        val contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), id.toLong()
                        )
                        realPath = getDataColumn(contentUri, null, null)
                    }
                    "com.android.providers.media.documents" -> {
                        val docId = DocumentsContract.getDocumentId(uri)
                        val split = docId.split(":")
                        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        val selection = "_id=?"
                        val selectionArgs = arrayOf(split[1])
                        realPath = getDataColumn(contentUri, selection, selectionArgs)
                    }
                }
            } else if ("content".equals(uri.scheme, true)) {
                realPath = getDataColumn(uri, null, null)
            } else if ("file".equals(uri.scheme, true)) {
                realPath = uri.path
            }
        }
        return realPath
    }

    private fun getDataColumn(uri: Uri, selection: String?, selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)

        try {
            cursor = contentResolver.query(uri, projection, selection, selectionArgs, null)
            cursor?.let {
                if (it.moveToFirst()) {
                    val columnIndex = it.getColumnIndexOrThrow(column)
                    return it.getString(columnIndex)
                }
            }
        } finally {
            cursor?.close()
        }
        return null
    }

}