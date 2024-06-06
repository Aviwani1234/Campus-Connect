package com.example.campus_connect_app.model


data class MessageModal(
    val sender: String,
    val message: String? = null,
    val question: String? = null,
    val option1: String? = null,
    val option2: String? = null,
    val option3: String? = null,
    val option4: String? = null,
    val option5: String? = null,
    val option6: String? = null,
)

