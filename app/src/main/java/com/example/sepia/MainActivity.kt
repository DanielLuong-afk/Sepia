package com.example.sepia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sepia.ui.theme.NotesGalleryScreen
import com.example.sepia.ui.theme.SepiaTheme
import com.example.sepia.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SepiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel: NotesViewModel = remember { NotesViewModel() }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NotesGalleryScreen(viewModel, innerPadding)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SepiaTheme {
        MainScreen()
    }
}