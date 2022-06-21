package com.slyrand.data.user.network

import com.slyrand.data.core.network.model.ApiListResponseBody
import com.slyrand.data.user.network.model.ApiUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    companion object {
        private const val V1 = "/data/v1"
        private const val PAGE = "page"
        private const val LIMIT = "limit"
        private const val USER = "/user/"
    }

    @GET("$V1$USER")
    suspend fun getUsers(
        @Query(PAGE) page: Int,
        @Query(LIMIT) limit: Int,
    ): ApiListResponseBody<ApiUser>

    @GET("$V1$USER/{id}")
    suspend fun getUserDetail(
        @Path("id") userId: String,
    ): ApiUser
}