package com.slyrand.data.core.network

import arrow.core.Either
import arrow.core.right
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.DataError

open class NetworkDataSource {
    protected fun <T> handleCall(method: () -> T): DataResult<T> = try {
        method().right()
    } catch (exception: Exception) {
        Either.Left(DataError.GenericException)
    }
}
