package com.slyrand.data.di

import com.slyrand.data.character.CharacterRepository
import com.slyrand.domain.character.ICharacterRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<ICharacterRepository> {
        CharacterRepository(networkDatasource = get())
    }
}