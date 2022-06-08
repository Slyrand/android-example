package com.slyrand.data.character

import arrow.core.Either
import com.slyrand.data.character.network.CharacterNetworkDatasource
import com.slyrand.domain.core.model.DataException
import com.slyrand.domain.character.ICharacterRepository
import com.slyrand.domain.character.model.Character

class CharacterRepository(
    private val networkDatasource: CharacterNetworkDatasource
) : ICharacterRepository {

    override suspend fun getCharacters(): Either<DataException, List<Character>> =
        networkDatasource.getCharacters()
}