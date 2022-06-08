package com.slyrand.example.di

import com.slyrand.example.ui.character.list.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val characterModule: Module = module {
    viewModel { CharactersViewModel(repository = get()) }
}