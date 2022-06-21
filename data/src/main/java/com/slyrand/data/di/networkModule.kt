package com.slyrand.data.di

import com.slyrand.data.user.network.UserService
import com.slyrand.data.core.network.ApiClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {
    single {
        ApiClient().createService(UserService::class.java)
    }
}