package com.slyrand.domain.core.model

sealed class DataError {
    object GenericException: DataError()
}