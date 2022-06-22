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
import com.slyrand.example.ui.user.list.UserListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.UserList.route
    ) {
        //userListNav(navController = navController)
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.userListNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.UserList).route,
        route = Feature.UserList.route
    ) {
        composable(NavCommand.ContentType(Feature.UserList)) {
            UserListScreen(onClick = {})
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