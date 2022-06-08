package com.slyrand.example.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    private val feature: Feature,
    private val navArguments: List<NavArg> = emptyList()
    ) {

    class ContentType(feature: Feature): NavCommand(feature)

    class ContentTypeDetail(feature: Feature) : NavCommand(feature, listOf(NavArg.ItemId))

    val route = run {
        val argValues = navArguments.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArguments.map {
        navArgument(it.key) { type = it.navType}
    }

    sealed class NavArg(val key: String, val navType: NavType<*>) {
        object ItemId: NavArg("itemId", NavType.IntType)
    }
}

