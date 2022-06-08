package com.slyrand.example.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.slyrand.example.ui.character.list.CharacterListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.CharacterList.route
    ) {
        //characterListNav(navController = navController)
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.characterListNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CharacterList).route,
        route = Feature.CharacterList.route
    ) {
        composable(NavCommand.ContentType(Feature.CharacterList)) {
            CharacterListScreen(onClick = {})
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) { content(it) }
}