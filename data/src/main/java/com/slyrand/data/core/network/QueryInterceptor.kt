package com.slyrand.data.core.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class QueryInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("app-id", "6297395fabb9941ade3d5b49")
            .build()

        return chain.proceed(request)
    }
}