package com.codylab.videocatalogue

import com.codylab.videocatalogue.core.api.CatalogueRepository
import com.codylab.videocatalogue.core.coroutine.ScopedViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository
) : ScopedViewModel() {

    fun setup() {

        launch {
            val categories = catalogueRepository.getCategories()
        }
    }
}