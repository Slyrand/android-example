package com.slyrand.domain.core

import arrow.core.Either
import com.slyrand.domain.core.model.DataError

typealias DataResult<T> = Either<DataError, T>