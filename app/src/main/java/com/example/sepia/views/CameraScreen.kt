package com.example.sepia.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.sepia.ui.theme.SepiaColors
import com.example.sepia.ui.theme.components.SepiaTopBar
import com.example.sepia.viewmodel.NotesViewModel
import com.example.sepia.viewmodel.OcrUiState


@Composable
fun CameraScreen(viewModel: NotesViewModel, innerPadding: PaddingValues) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val ocrState by viewModel.ocrState.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        viewModel.resetOcrState()
    }

    Scaffold(
        topBar = { SepiaTopBar(title = "Camera") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { imagePickerLauncher.launch("image/*") },
                containerColor = SepiaColors.Primary,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()) // ← FIX
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Pick Image")
            }
        },
        containerColor = SepiaColors.ScreenBackground
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SepiaColors.ScreenBackground)
                .padding(scaffoldPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (selectedImageUri != null) {
                AsyncImage(
                    model = selectedImageUri,
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Button(
                    onClick = { viewModel.extractTextFromImage(selectedImageUri!!) },
                    colors = ButtonDefaults.buttonColors(containerColor = SepiaColors.Primary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Extract Text (OCR)")
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Tap + to select an image",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            when (val state = ocrState) {
                is OcrUiState.Loading -> {
                    CircularProgressIndicator(color = SepiaColors.Primary)
                    Text("Extracting text, please wait...")
                }
                is OcrUiState.Success -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Extracted Text:",
                                style = MaterialTheme.typography.titleMedium,
                                color = SepiaColors.Primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.text,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                is OcrUiState.Error -> {
                    Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                }
                is OcrUiState.Idle -> {}
            }
        }
    }
}