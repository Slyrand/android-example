package com.slyrand.data.di

import com.slyrand.data.user.UserRepository
import com.slyrand.data.user.network.UserNetworkDatasource
import org.koin.core.module.Module
import org.koin.dsl.module

val datasourceModule: Module = module {
    single<UserRepository.INetworkDatasource> { UserNetworkDatasource(get()) }
}