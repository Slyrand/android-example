package com.slyrand.domain.user

import com.slyrand.domain.user.model.User
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.PaginationState

interface IUserRepository {
    suspend fun getUsers(paginationState: PaginationState): DataResult<List<User>>
    suspend fun getUserDetail(userId: String): DataResult<User>
}