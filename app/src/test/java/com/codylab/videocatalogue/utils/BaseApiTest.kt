package com.codylab.videocatalogue.utils

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseApiTest {

    lateinit var server: MockWebServer

    internal fun <T> getRetrofitApi(apiClass: Class<T>): T {
        val clientBuilder = OkHttpClient.Builder()
        val apiAdapter = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return apiAdapter.create(apiClass)
    }


    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}