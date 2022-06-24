package com.slyrand.data.di

import com.slyrand.data.user.UserRepository
import com.slyrand.domain.user.IUserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<IUserRepository> {
        UserRepository(localDatasource = get(), networkDatasource = get())
    }
}