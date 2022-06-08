package com.slyrand.data.di

import com.slyrand.data.character.network.CharacterService
import com.slyrand.data.core.network.ApiClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {
    single { ApiClient.createService(CharacterService::class.java) }
}