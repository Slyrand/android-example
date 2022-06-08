package com.slyrand.data.di

import com.slyrand.data.character.CharacterRepository
import com.slyrand.data.character.network.CharacterNetworkDatasource
import org.koin.core.module.Module
import org.koin.dsl.module

val datasourceModule: Module = module {
    single<CharacterRepository.INetworkDatasource> { CharacterNetworkDatasource(get()) }
}