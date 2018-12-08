package com.codylab.videocatalogue.catalogue

import android.arch.lifecycle.MutableLiveData
import com.codylab.videocatalogue.core.api.CatalogueRepository
import com.codylab.videocatalogue.core.coroutine.DispatcherManager
import com.codylab.videocatalogue.core.coroutine.ScopedViewModel
import com.codylab.videocatalogue.core.livedata.Event
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueViewModel @Inject constructor(
    val catalogueRepository: CatalogueRepository,
    var dispatcherManager: DispatcherManager
) : ScopedViewModel(dispatcherManager) {

    val uiModelLiveData = MutableLiveData<CatalogueUIModel>()
    val currentUIModel = CatalogueUIModel()

    fun onLoad() = launch {
        uiModelLiveData.value = currentUIModel.apply {
            isLoading = true
            hasError = false
        }.copy()

        try {
            uiModelLiveData.value = currentUIModel.apply {
                categories = catalogueRepository.getCategories()
            }.copy()
        } catch (t: Throwable) {
            uiModelLiveData.value = currentUIModel.apply {
                message = Event(t.toString())
                hasError = true
            }.copy()
        } finally {
            uiModelLiveData.value = currentUIModel.apply {
                isLoading = false
            }.copy()
        }
    }

    fun onSwipeRefresh() = onLoad()
}