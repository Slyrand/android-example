package com.slyrand.data.user.network

import com.slyrand.data.core.network.handleCall
import com.slyrand.data.user.UserRepository
import com.slyrand.data.user.network.model.asUser
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.PaginationState
import com.slyrand.domain.user.model.User

class UserNetworkDatasource(
    private val userService: UserService
) : UserRepository.INetworkDatasource {

    override suspend fun getUsers(
        paginationState: PaginationState
    ): DataResult<List<User>> = handleCall {
        userService
            .getUsers(page = paginationState.page, limit = paginationState.limit)
            .data
            .map { it.asUser() }
    }

    override suspend fun getUserDetail(userId: String): DataResult<User> = handleCall {
        userService
            .getUserDetail(userId)
            .asUser()
    }
}