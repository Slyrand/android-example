package com.slyrand.data.core.network.model

data class ApiResponseBody<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: ApiResponseData<T>
)