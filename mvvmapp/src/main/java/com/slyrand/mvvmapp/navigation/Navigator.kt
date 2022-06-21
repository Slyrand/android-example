package com.slyrand.mvvmapp.navigation

import androidx.navigation.NavController
import com.slyrand.mvvmapp.user.list.UserListFragmentDirections

class Navigator(
    private val navController: NavController,
) {

    fun navigateToUserDetail(userId: String) {
        /*val action = UserListFragmentDirections.actionUserListToUserDetail(userId)
        navController.navigate(action)*/
    }
}