package com.slyrand.example.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slyrand.example.ui.navigation.Navigation
import com.slyrand.example.ui.theme.ExampleTheme

@ExperimentalFoundationApi
@Composable
fun MainApp(appState: AppState = rememberAppState()) {
    MainScreen {
        Box(modifier = Modifier.fillMaxSize()) {
            Navigation(navController = appState.navController)
        }
    }
}

@Composable
fun MainScreen(content: @Composable () -> Unit) {
    ExampleTheme {
        Surface(color = MaterialTheme.colors.background) {
           content()
        }
    }
}