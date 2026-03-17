package com.example.sepia.views

import android.graphics.Camera
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sepia.data.Note
import com.example.sepia.ui.theme.SepiaColors
import com.example.sepia.ui.theme.components.CategoryChips
import com.example.sepia.ui.theme.components.NoteCard
import com.example.sepia.ui.theme.components.SearchBar
import com.example.sepia.ui.theme.components.SepiaTopBar
import com.example.sepia.viewmodel.NotesViewModel

@Composable
fun CameraScreen() {

    Scaffold(
        topBar = {
            SepiaTopBar(title = "Camera")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add new note */ },
                containerColor = SepiaColors.Primary,
                contentColor = androidx.compose.ui.graphics.Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add note")
            }
        },
        containerColor = SepiaColors.ScreenBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SepiaColors.ScreenBackground)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
        }}}