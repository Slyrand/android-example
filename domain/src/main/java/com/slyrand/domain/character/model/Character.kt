package com.slyrand.domain.character.model

data class Character(
    val id: String?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceUri: String?,
    val urls: List<String>?,
    val thumbnail: String?,
    val comics: List<String>,
    val stories: List<String>,
    val events: List<String>,
    val series: List<String>,
)