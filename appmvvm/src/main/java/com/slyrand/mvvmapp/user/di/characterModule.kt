package com.slyrand.mvvmapp.user.di

import com.slyrand.domain.user.usecase.GetUsers
import com.slyrand.mvvmapp.user.detail.UsersDetailViewModel
import com.slyrand.mvvmapp.user.list.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val userModule: Module = module {
    viewModel { UsersListViewModel(get()) }
    viewModel { (userId: String) -> UsersDetailViewModel(userId, get()) }
    single { GetUsers(get()) }
}