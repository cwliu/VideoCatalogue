package com.codylab.videocatalogue.core

import com.codylab.videocatalogue.core.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepository @Inject constructor(
    private val catalogueAPI: CatalogueAPI
) {

    suspend fun getCategories(): List<Category> {
        return withContext(Dispatchers.IO) {
            catalogueAPI.getVideos().await()
        }
    }
}