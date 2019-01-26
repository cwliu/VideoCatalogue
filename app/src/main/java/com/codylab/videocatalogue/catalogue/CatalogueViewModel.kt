package com.codylab.videocatalogue.catalogue

import com.codylab.videocatalogue.core.api.CatalogueRepository
import com.codylab.videocatalogue.core.coroutine.DispatcherManager
import com.codylab.videocatalogue.core.coroutine.ScopedViewModel
import com.codylab.videocatalogue.core.livedata.Event
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueViewModel @Inject constructor(
    dispatcherManager: DispatcherManager,
    private val catalogueRepository: CatalogueRepository
) : ScopedViewModel<CatalogueUIModel>(dispatcherManager) {

    fun onLoad() = launch {
        CatalogueUIModel(isLoading = true).emit()

        try {
            CatalogueUIModel(categories = catalogueRepository.getCategories()).emit()
        } catch (t: Throwable) {
            CatalogueUIModel(hasError = true, message = Event(t.toString())).emit()
        }
    }

    fun onSwipeRefresh() = onLoad()
}