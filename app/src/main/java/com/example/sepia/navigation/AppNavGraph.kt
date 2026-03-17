package com.example.sepia.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sepia.views.NotesGalleryScreen
import com.example.sepia.views.SettingsScreen
import com.example.sepia.viewmodel.NotesViewModel
import com.example.sepia.views.CameraScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: NotesViewModel,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController    = navController,
        startDestination = Routes.HOME,
        modifier         = modifier
    ) {
        composable(Routes.HOME) {
            NotesGalleryScreen(viewModel, innerPadding)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
        composable(Routes.CAMERA){
            CameraScreen()
        }
    }
}