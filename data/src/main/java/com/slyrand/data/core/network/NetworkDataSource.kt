package com.slyrand.data.core.network

import android.util.Log
import arrow.core.Either
import arrow.core.right
import com.slyrand.domain.core.DataResult
import com.slyrand.domain.core.model.DataError

inline fun <T> handleCall(method: () -> T): DataResult<T> = try {
    method().right()
} catch (exception: Exception) {
    Log.e("NetworkDataSource", exception.stackTraceToString())
    Either.Left(DataError.GenericException)
}
