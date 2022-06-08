package com.slyrand.data.character.network

import arrow.core.Either
import com.slyrand.data.core.network.NetworkDataSource
import com.slyrand.domain.core.model.DataException
import com.slyrand.domain.character.model.Character

class CharacterNetworkDatasource(
    private val characterService: CharacterService
): NetworkDataSource() {

    fun getCharacters(): Either<DataException, List<Character>> = handleCall {
        characterService.getCharacters()
    }.map { result -> result.data.results.map { it.mapToDomain() } }
}