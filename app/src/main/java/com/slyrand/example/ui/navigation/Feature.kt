package com.slyrand.example.ui.navigation

sealed class Feature(val route: String) {
    companion object {
        const val CHARACTERS = "characters"
        const val CHARACTER_DETAIL = "character_detail"
    }

    object CharacterList : Feature(CHARACTERS)
    object CharactersDetail : Feature(CHARACTER_DETAIL)
}