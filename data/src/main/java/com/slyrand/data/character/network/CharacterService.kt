package com.slyrand.data.character.network

import com.slyrand.data.core.network.model.ApiResponseBody
import com.slyrand.data.character.network.model.ApiCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    companion object {
        const val V1 = "/v1/public"
        const val CHARACTERS = "/characters"
        const val API_KEY = "apikey"
    }

    @GET("$V1$CHARACTERS")
    fun getCharacters(
        @Query(API_KEY) apiKey: String = "8ff319f4062738b5f5fd405f118a0d8e"
    ): ApiResponseBody<ApiCharacter>
}