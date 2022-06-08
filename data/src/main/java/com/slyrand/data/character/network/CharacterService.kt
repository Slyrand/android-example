package com.slyrand.data.character.network

import com.slyrand.data.core.network.model.ApiResponseBody
import com.slyrand.data.character.network.model.ApiCharacter
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {

    companion object {
        const val V1 = "/v1/public"
        const val CHARACTERS = "/characters"
    }

    @GET("$V1$CHARACTERS")
    fun getCharacters(): Call<ApiResponseBody<ApiCharacter>>
}