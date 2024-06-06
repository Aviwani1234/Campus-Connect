package com.example.campus_connect_app.placement

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campus_connect_app.R
import com.example.campus_connect_app.databinding.ActivityPdfviewBinding
import com.example.campus_connect_app.model.placement.AppliedStudents
import com.example.campus_connect_app.model.placement.CampusDrive
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class PDFViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPdfviewBinding
    private lateinit var pdfView: PDFView
    var studDetails : AppliedStudents? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityPdfviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        val pdfByteArray = intent.getByteArrayExtra("pdf")
        pdfView = binding.pdfView
         studDetails = intent.getParcelableExtra<AppliedStudents>("studDetails")
        Log.d("pdfIs : ", studDetails?.pdf.toString())

        val pdfFile = createTemporaryFile(studDetails?.pdf)
        pdfView.fromFile(pdfFile).load()

    }

    private fun createTemporaryFile(pdfByteArray: ByteArray?): File {
        val tempFile = File(cacheDir, "temp.pdf")
        try {
            val fos = FileOutputStream(tempFile)
            fos.write(pdfByteArray)
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return tempFile
    }
}