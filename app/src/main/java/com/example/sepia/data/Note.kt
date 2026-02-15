package com.example.sepia.data

data class Note(
    val id: Int,
    val category: String,
    val content: String = ""
)