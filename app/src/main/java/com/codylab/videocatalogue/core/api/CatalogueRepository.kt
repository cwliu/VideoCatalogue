package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.core.coroutine.DispatcherManager
import com.codylab.videocatalogue.core.model.Category
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepository @Inject constructor(
    private val catalogueAPI: CatalogueAPI,
    private val dispatcherManager: DispatcherManager
) {

    suspend fun getCategories(): List<Category> {
        return withContext(dispatcherManager.io) {
            catalogueAPI.getCategories().await()
        }
    }
}