package com.slyrand.data.core.network.model

data class ApiListResponseBody<T>(
    val data: List<T>,
    val total: Int,
    val page: Int,
    val limit: Int
)