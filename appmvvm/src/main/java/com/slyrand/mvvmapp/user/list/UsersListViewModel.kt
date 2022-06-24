package com.slyrand.mvvmapp.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slyrand.domain.user.IUserRepository
import com.slyrand.domain.core.model.DataError
import com.slyrand.domain.core.model.PaginationState
import com.slyrand.domain.user.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersListViewModel(
    private val userRepository: IUserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        requestData()
    }

    fun refresh() {
        _state.value = state.value.copy(
            paginationState = PaginationState(),
            users = emptyList()
        )
        requestData()
    }

    private fun requestData() {
        viewModelScope.launch {
            _state.value = state.value.copy(loading = true, error = null)
            userRepository.getUsers(state.value.paginationState).fold(
                { _state.value = state.value.copy(error = it) },
                { _state.value = state.value.copy(
                    users = it,
                    error = null
                ) }
            )
            _state.value = state.value.copy(loading = false)
        }
    }

    data class UiState(
        val paginationState: PaginationState = PaginationState(),
        val loading: Boolean = false,
        val error: DataError? = null,
        val users: List<User> = emptyList(),
    )
}