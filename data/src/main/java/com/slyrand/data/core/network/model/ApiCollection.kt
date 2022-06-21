package com.slyrand.data.core.network.model

import com.slyrand.domain.core.model.CollectionItem

data class ApiCollection(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ApiCollectionItem>
)