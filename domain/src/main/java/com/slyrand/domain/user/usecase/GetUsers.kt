package com.slyrand.domain.user.usecase

import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.PaginationState
import com.slyrand.domain.user.IUserRepository
import com.slyrand.domain.user.model.User

class GetUsers(private val userRepository: IUserRepository) {

    suspend fun execute(
        paginationState: PaginationState,
        query: String = ""
    ): DataResult<List<User>> {
        return if (query.isEmpty()) userRepository.getUsers(paginationState)
        else userRepository.queryUsers(query)
    }
}