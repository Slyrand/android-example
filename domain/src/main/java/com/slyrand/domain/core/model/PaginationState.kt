package com.slyrand.domain.core.model

data class PaginationState(
    val page: Int = 0,
    val limit: Int = 20,
) {
    fun nextPage(): PaginationState = copy(page = page + 1)
}