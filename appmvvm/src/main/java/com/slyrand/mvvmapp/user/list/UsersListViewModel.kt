package com.slyrand.mvvmapp.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slyrand.domain.core.model.DataError
import com.slyrand.domain.core.model.PaginationState
import com.slyrand.domain.user.model.User
import com.slyrand.domain.user.usecase.GetUsers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersListViewModel(
    private val getUsers: GetUsers,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        requestData()
    }

    fun refresh() {
        _state.value = _state.value.copy(
            paginationState = PaginationState(),
            users = emptyList()
        )
        requestData()
    }

    fun loadMore() {
        if (_state.value.query.isEmpty()) requestData()
    }

    private fun requestData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            getUsers.execute(_state.value.paginationState, _state.value.query).fold(
                { _state.value = _state.value.copy(error = it) },
                {
                    _state.value = _state.value.copy(
                        paginationState = _state.value.paginationState.nextPage(),
                        users = _state.value.users + it,
                        error = null
                    )
                }
            )
            _state.value = _state.value.copy(loading = false)
        }
    }

    fun onQueryChanged(query: String) {
        _state.value = _state.value.copy(
            users = listOf(),
            paginationState = PaginationState(),
            query = query,
            loading = true,
            error = null
        )
        requestData()
    }

    data class UiState(
        val paginationState: PaginationState = PaginationState(),
        val loading: Boolean = false,
        val query: String = "",
        val error: DataError? = null,
        val users: List<User> = listOf(),
    )
}