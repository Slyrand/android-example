package com.slyrand.data.core.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(
    private val privateKey: String,
    private val publicKey: String,
) {

    fun <S> createService(serviceClass: Class<S>): S {
        val httpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        with(httpClient) {
            addInterceptor(QueryInterceptor(privateKey, publicKey))
            readTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
        }

        val retrofit = getRetrofitClient(httpClient.build())
        return retrofit.create(serviceClass)
    }


    private fun getRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        val baseUrl = "https://gateway.marvel.com/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}