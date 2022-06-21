package com.slyrand.data.core.network.model

import com.slyrand.domain.core.model.CollectionItem

data class ApiCollectionItem(
    val resourceURI: String,
    val name: String,
    val type: String? = null,
) {

    fun asCollectionItem() = CollectionItem(
        name = name,
        type = type
    )
}