package com.slyrand.data.user

import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.PaginationState
import com.slyrand.domain.user.IUserRepository
import com.slyrand.domain.user.model.User

class UserRepository(
    private val localDatasource: ILocalDatasource,
    private val networkDatasource: INetworkDatasource,
) : IUserRepository {

    override suspend fun getUsers(paginationState: PaginationState): DataResult<List<User>> =
        networkDatasource.getUsers(paginationState).map { users ->
            localDatasource.addUsers(users)
            users
        }

    override suspend fun getUserDetail(userId: String): DataResult<User> =
        networkDatasource.getUserDetail(userId)

    override fun queryUsers(query: String): DataResult<List<User>> =
        localDatasource.queryUsers(query)

    interface INetworkDatasource {
        suspend fun getUsers(paginationState: PaginationState): DataResult<List<User>>
        suspend fun getUserDetail(userId: String): DataResult<User>
    }

    interface ILocalDatasource {
        fun queryUsers(query: String): DataResult<List<User>>
        fun addUsers(users: List<User>)
    }
}

