package com.slyrand.data.character

import com.slyrand.domain.character.ICharacterRepository
import com.slyrand.domain.character.model.Character
import com.slyrand.domain.core.DataResult

class CharacterRepository(
    private val networkDatasource: INetworkDatasource
) : ICharacterRepository {

    override suspend fun getCharacters(): DataResult<List<Character>> =
        networkDatasource.getCharacters()

    interface INetworkDatasource {
        suspend fun getCharacters(): DataResult<List<Character>>
    }
}

