package com.slyrand.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

open class NetworkDataSourceTest {
    protected var mockRetrofit: MockRetrofit

    init {
        val retrofit = Retrofit.Builder().baseUrl("http://test.com")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        val behavior = NetworkBehavior.create()

        mockRetrofit = MockRetrofit.Builder(retrofit)
            .networkBehavior(behavior)
            .build()
    }
}
