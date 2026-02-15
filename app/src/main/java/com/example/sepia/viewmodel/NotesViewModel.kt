package com.example.sepia.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sepia.data.Note

class NotesViewModel : ViewModel() {
    var selectedCategory = "All"
        private set

    private val notes = listOf(
        Note(1, "Math101"),
        Note(2, "Physics"),
        Note(3, "Work"),
        Note(4, "Math101")
    )

    fun getFilteredNotes(): List<Note> {
        return if (selectedCategory == "All") notes else notes.filter { it.category == selectedCategory }
    }

    fun selectCategory(category: String) {
        selectedCategory = category
    }
}