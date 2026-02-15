package com.example.sepia.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sepia.data.Note
import com.example.sepia.ui.theme.components.CategoryChips
import com.example.sepia.ui.theme.components.NoteCard
import com.example.sepia.ui.theme.components.SearchBar
import com.example.sepia.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesGalleryScreen(viewModel: NotesViewModel, paddingValues: PaddingValues) {
    var selectedCategory by remember { mutableStateOf(viewModel.selectedCategory) }

    LaunchedEffect(selectedCategory) {
        viewModel.selectCategory(selectedCategory)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sepia") },
                actions = {
                    IconButton(onClick = { /* Settings */ }) { /* Icon */ }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Add new note */ }) { /* Icon */ }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            SearchBar(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))

            CategoryChips(
                categories = listOf("All", "Math101", "Physics", "Work"),
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            val filteredNotes = viewModel.getFilteredNotes()
            NotesGrid(notes = filteredNotes)
        }
    }
}

@Composable
fun NotesGrid(notes: List<Note>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(notes) { note ->
            NoteCard(note = note)
        }
    }
}