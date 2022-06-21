package com.slyrand.data.user

import com.slyrand.domain.user.IUserRepository
import com.slyrand.domain.user.model.User
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.PaginationState

class UserRepository(
    private val networkDatasource: INetworkDatasource
) : IUserRepository {

    override suspend fun getUsers(paginationState: PaginationState): DataResult<List<User>> =
        networkDatasource.getUsers(paginationState)

    override suspend fun getUserDetail(userId: String): DataResult<User> =
        networkDatasource.getUserDetail(userId)

    interface INetworkDatasource {
        suspend fun getUsers(paginationState: PaginationState): DataResult<List<User>>
        suspend fun getUserDetail(userId: String): DataResult<User>
    }
}

