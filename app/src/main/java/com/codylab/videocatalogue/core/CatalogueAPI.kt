package com.codylab.videocatalogue.core

import com.codylab.videocatalogue.core.model.Category
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CatalogueAPI {
    @GET("video-catalogue/data.json")
    fun getVideos(): Deferred<List<Category>>
}