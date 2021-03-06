package com.slyrand.mvvmapp.user.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slyrand.domain.user.IUserRepository
import com.slyrand.domain.core.model.DataError
import com.slyrand.domain.user.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersDetailViewModel(
    private val userId: String,
    private val userRepository: IUserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(loading = true)
            userRepository.getUserDetail(userId).fold(
                { _state.value = state.value.copy(error = it) },
                { _state.value = state.value.copy(user = it) }
            )
            _state.value = state.value.copy(loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val error: DataError? = null,
        val user: User? = null,
    )
}