package com.slyrand.mvvmapp.character.di

import com.slyrand.mvvmapp.character.list.CharactersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val characterModule: Module = module {
    viewModel { CharactersListViewModel(get()) }
}