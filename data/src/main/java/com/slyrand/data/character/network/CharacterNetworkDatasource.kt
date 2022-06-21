package com.slyrand.data.character.network

import com.slyrand.data.character.CharacterRepository
import com.slyrand.data.core.network.handleCall
import com.slyrand.domain.character.model.Character
import com.slyrand.domain.core.DataResult

class CharacterNetworkDatasource(
    private val characterService: CharacterService
) : CharacterRepository.INetworkDatasource {

    override suspend fun getCharacters(): DataResult<List<Character>> = handleCall {
        characterService
            .getCharacters()
            .data
            .results
            .map { it.asCharacter() }
    }

}