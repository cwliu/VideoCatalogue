package com.codylab.videocatalogue.catalogue

import android.arch.lifecycle.LiveData
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
    dispatcherManager: DispatcherManager,
    private val catalogueRepository: CatalogueRepository
) : ScopedViewModel(dispatcherManager) {

    private val _uiModelLiveData = MutableLiveData<CatalogueUIModel>()
    val uiModelLiveData: LiveData<CatalogueUIModel> = _uiModelLiveData

    private fun CatalogueUIModel.emit() {
        _uiModelLiveData.value = this
    }

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