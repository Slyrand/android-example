package com.slyrand.domain.character

import arrow.core.Either
import com.slyrand.domain.core.model.DataException
import com.slyrand.domain.character.model.Character

interface ICharacterRepository {
    suspend fun getCharacters(): Either<DataException, List<Character>>
}