package com.slyrand.domain.character.model

import com.slyrand.domain.core.model.CollectionItem

data class Character(
    val id: String?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceUri: String?,
    val thumbnail: String?,
    val comics: List<CollectionItem>,
    val stories: List<CollectionItem>,
)