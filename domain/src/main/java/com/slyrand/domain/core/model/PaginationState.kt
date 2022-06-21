package com.slyrand.domain.core.model

data class PaginationState(
    val page: Int = 1,
    val limit: Int = 20,
) {
    fun setNextPage(): PaginationState = copy(page = page + 1)
}