package com.slyrand.data.user.network

import com.slyrand.data.core.network.model.ApiListResponseBody
import com.slyrand.data.user.network.model.ApiUser
import com.slyrand.data.user.network.model.ApiUserObjectMother
import retrofit2.mock.BehaviorDelegate

class MockUserService(
    private val delegate: BehaviorDelegate<UserService>,
) : UserService {
    override suspend fun getUsers(page: Int, limit: Int): ApiListResponseBody<ApiUser> = delegate
        .returningResponse(
            ApiListResponseBody(
                data = listOf(ApiUserObjectMother.getGenericUser()),
                total = 1,
                page = page,
                limit = limit
            )
        )
        .getUsers(page, limit)

    override suspend fun getUserDetail(userId: String): ApiUser = delegate
        .returningResponse(ApiUserObjectMother.getGenericUser())
        .getUserDetail(userId)
}
