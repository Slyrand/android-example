package com.slyrand.data.core.network

import com.slyrand.domain.core.generateHash
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor constructor(
    private val privateKey: String,
    private val publicKey: String,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val requestBuilder = originalRequest.newBuilder()
        val timestamp = System.currentTimeMillis()

        generateHash(timestamp, privateKey, publicKey).map {
            val url = originalUrl.newBuilder()
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("ts", timestamp.toString())
                .addQueryParameter("hash", it)
                .build()

            return chain.proceed(
                requestBuilder
                .url(url)
                .build()
            )
        }

        return chain.proceed(requestBuilder.build())
    }
}