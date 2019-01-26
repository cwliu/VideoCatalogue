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

    private fun emitUiModel(uiModel: CatalogueUIModel) {
        _uiModelLiveData.value = uiModel
    }

    fun onLoad() = launch {
        emitUiModel(CatalogueUIModel(isLoading = true))

        try {
            emitUiModel(CatalogueUIModel(categories = catalogueRepository.getCategories()))
        } catch (t: Throwable) {
            emitUiModel(CatalogueUIModel(hasError = true, message = Event(t.toString())))
        }
    }

    fun onSwipeRefresh() = onLoad()
}