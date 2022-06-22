package com.slyrand.example.ui.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.slyrand.domain.user.IUserRepository
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.PaginationState
import com.slyrand.domain.user.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = State(loading = true)
            _state.value = State(users = userRepository.getUsers(PaginationState()))
        }
    }

    data class State(
        val loading: Boolean = false,
        val users: DataResult<List<User>> = emptyList<User>().right()
    )
}