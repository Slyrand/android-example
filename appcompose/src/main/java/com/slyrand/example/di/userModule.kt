package com.slyrand.example.di

import com.slyrand.example.ui.user.list.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val userModule: Module = module {
    viewModel { UsersViewModel(userRepository = get()) }
}