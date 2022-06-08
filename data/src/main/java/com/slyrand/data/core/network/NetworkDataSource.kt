package com.slyrand.data.core.network

import arrow.core.Either
import com.slyrand.domain.core.model.DataException
import retrofit2.Call

open class NetworkDataSource {
    protected fun <T> handleCall(method: () -> Call<T>): Either<DataException, T> {
        try {
            method().execute().let {
                return if (it.isSuccessful && it.body() != null) Either.Right(it.body()!!)
                else Either.Left(DataException.GenericException)
            }
        } catch (exception: Exception) {
            return Either.Left(DataException.GenericException)
        }
    }
}