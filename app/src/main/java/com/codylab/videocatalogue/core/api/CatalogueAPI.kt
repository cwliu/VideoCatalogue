package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.core.model.Category
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CatalogueAPI {
    @GET("video-catalogue/data.json")
    fun getCategories(): Deferred<List<Category>>
}