package com.slyrand.domain.core.model

sealed class DataException: Exception() {
    object GenericException: DataException()
}