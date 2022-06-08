package com.slyrand.domain.character

import com.slyrand.domain.character.model.Character
import com.slyrand.domain.core.DataResult

interface ICharacterRepository {
    suspend fun getCharacters(): DataResult<List<Character>>
}