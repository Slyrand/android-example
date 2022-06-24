package com.slyrand.domain.core.model

sealed class DataError {
    object GenericError : DataError()
    object ConnectionError : DataError()
    object ServerError : DataError()
    class NoResultsFoundFor(val query: String) : DataError()
}