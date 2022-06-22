package com.slyrand.example.ui.navigation

sealed class Feature(val route: String) {
    companion object {
        const val USERS = "users"
        const val USER_DETAIL = "user_detail"
    }

    object UserList : Feature(USERS)
    object UserDetail : Feature(USER_DETAIL)
}