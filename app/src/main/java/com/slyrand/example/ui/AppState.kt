package com.slyrand.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState = remember(navController, coroutineScope) {
    AppState(navController = navController, coroutineScope = coroutineScope)
}


class AppState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope
) {
}