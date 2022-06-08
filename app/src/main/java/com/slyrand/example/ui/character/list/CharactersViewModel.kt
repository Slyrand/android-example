package com.slyrand.example.ui.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.slyrand.domain.character.ICharacterRepository
import com.slyrand.domain.character.model.Character
import com.slyrand.domain.core.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel(
    private val repository: ICharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = State(loading = true)
            _state.value = State(characters = repository.getCharacters())
        }
    }

    data class State(
        val loading: Boolean = false,
        val characters: DataResult<List<Character>> = emptyList<Character>().right()
    )
}