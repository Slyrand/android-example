package com.slyrand.mvvmapp.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.slyrand.domain.character.ICharacterRepository
import com.slyrand.domain.character.model.Character
import com.slyrand.domain.core.model.DataError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersListViewModel(
    private val characterRepository: ICharacterRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(loading = true)
            delay(2000)
            characterRepository.getCharacters().fold(
                { _state.value = state.value.copy(error = it) },
                { _state.value = state.value.copy(characters = it) }
            )
            _state.value = state.value.copy(loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val error: DataError? = null,
        val characters: List<Character> = emptyList(),
    )
}

@Suppress("UNCHECKED_CAST")
class CharacterListViewModelFactory(
    private val characterRepository: ICharacterRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersListViewModel(characterRepository) as T
    }
}