package com.slyrand.data.core.network

import arrow.core.left
import arrow.core.right
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.DataError
import retrofit2.HttpException
import java.io.IOException

inline fun <T> handleCall(method: () -> T): DataResult<T> = try {
    method().right()
} catch (exception: Exception) {
    exception.asDataError().left()
}

fun Exception.asDataError(): DataError = when (this) {
    is IOException -> DataError.ConnectionError
    is HttpException -> DataError.ServerError
    else -> DataError.GenericError
}