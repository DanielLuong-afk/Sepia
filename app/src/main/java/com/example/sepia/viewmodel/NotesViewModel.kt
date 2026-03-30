package com.example.sepia.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sepia.data.Note
import com.example.sepia.data.OcrRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
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

    private val ocrRepository = OcrRepository(getApplication())

    private val _ocrState = MutableStateFlow<OcrUiState>(OcrUiState.Idle)
    val ocrState: StateFlow<OcrUiState> = _ocrState.asStateFlow()

    fun extractTextFromImage(imageUri: Uri) {
        viewModelScope.launch {
            _ocrState.value = OcrUiState.Loading
            val result = ocrRepository.extractTextFromImage(imageUri)
            _ocrState.value = result.fold(
                onSuccess = { OcrUiState.Success(it) },
                onFailure = { OcrUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun resetOcrState() {
        _ocrState.value = OcrUiState.Idle
    }
}

sealed class OcrUiState {
    object Idle : OcrUiState()
    object Loading : OcrUiState()
    data class Success(val text: String) : OcrUiState()
    data class Error(val message: String) : OcrUiState()
}