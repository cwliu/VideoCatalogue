package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_BASE_URL = BuildConfig.API_BASE_URL

@Module
class CatalogueApiModule {

    @Singleton
    @Provides
    fun provideCatalogueApi(): CatalogueAPI {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatalogueAPI::class.java)
    }
}