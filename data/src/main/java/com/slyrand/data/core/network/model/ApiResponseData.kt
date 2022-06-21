package com.slyrand.data.core.network.model

class ApiResponseData<T>(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: T,
)