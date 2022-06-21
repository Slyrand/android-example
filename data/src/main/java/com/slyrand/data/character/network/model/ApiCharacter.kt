package com.slyrand.data.character.network.model

import com.slyrand.data.core.network.model.ApiCollection
import com.slyrand.domain.character.model.Character

data class ApiCharacter(
    val id: String?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: ApiThumbnail?,
    val comics: ApiCollection,
    val stories: ApiCollection,
) {

    fun asCharacter(): Character = Character(
        id = id,
        name = name,
        description = description,
        modified = modified,
        resourceUri = resourceURI,
        thumbnail = "${thumbnail?.path}${thumbnail?.extension}",
        comics = comics.items.map { it.asCollectionItem() },
        stories = stories.items.map { it.asCollectionItem() },
    )
}