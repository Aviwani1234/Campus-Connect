package com.example.campus_connect_app.model

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

data class Complaint(
    val id: String? = null,
    val userName: String? = null,
    val title: String? = null,
    val department: String? = null,
    val status: String? = null,
    val reply: String? = null,
//    val image: ByteArray? = null, // Change to ByteArray to store image as bytes
    val image: Int?, // Change to ByteArray to store image as bytes
    val description: String? = null)
//) {
//    constructor(
//        id: String?,
//        userName: String?,
//        title: String?,
//        department: String?,
//        status: String?,
//        reply: String?,
//        image: Int?,
//        description: String?
//    ) : this(
//        id,
//        userName,
//        title,
//        department,
//        status,
//        reply,
//        image,
//        description
//    )
//
//    companion object {
//        private fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? {
//            if (bitmap == null) return null
//            val stream = ByteArrayOutputStream()
//            bitmap.compress(CompressFormat.PNG, 0, stream)
//            return stream.toByteArray()
//        }
//    }
//}
