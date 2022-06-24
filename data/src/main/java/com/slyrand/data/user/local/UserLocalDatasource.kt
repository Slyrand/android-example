package com.slyrand.data.user.local

import arrow.core.left
import arrow.core.right
import com.slyrand.data.user.UserRepository
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.DataError
import com.slyrand.domain.user.model.User

class UserLocalDatasource : UserRepository.ILocalDatasource {

    private val _users: MutableSet<User> = mutableSetOf()

    override fun addUsers(users: List<User>) {
        _users.addAll(users)
    }

    override fun queryUsers(query: String): DataResult<List<User>> {
        val filteredUsers = _users.filter {
            val userInfo = "${it.firstName}, ${it.lastName}, ${it.title}, ${it.email}"
            userInfo.contains(query)
        }

        return if (filteredUsers.isNotEmpty()) filteredUsers.right()
        else DataError.NoResultsFoundFor(query).left()
    }
}