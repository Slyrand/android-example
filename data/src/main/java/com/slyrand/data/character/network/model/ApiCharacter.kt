package com.slyrand.data.character.network.model

import com.slyrand.domain.character.model.Character

data class ApiCharacter(
    val id: String?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val urls: List<String>?,
    val thumbnail: String?,
    val comics: List<String>,
    val stories: List<String>,
    val events: List<String>,
    val series: List<String>,
) {

    fun asCharacter(): Character = Character(
        id = id,
        name = name,
        description = description,
        modified = modified,
        resourceUri = resourceURI,
        urls = urls,
        thumbnail = thumbnail,
        comics = comics,
        stories = stories,
        events = events,
        series = series
    )
}