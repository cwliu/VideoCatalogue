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

    private val currentUIModel = CatalogueUIModel()

    private val _uiModelLiveData = MutableLiveData<CatalogueUIModel>()
    val uiModelLiveData: LiveData<CatalogueUIModel>
        get() = _uiModelLiveData

    fun onLoad() = launch {
        _uiModelLiveData.value = currentUIModel.apply {
            isLoading = true
            hasError = false
        }.copy()

        try {
            _uiModelLiveData.value = currentUIModel.apply {
                categories = catalogueRepository.getCategories()
            }.copy()
        } catch (t: Throwable) {
            _uiModelLiveData.value = currentUIModel.apply {
                message = Event(t.toString())
                hasError = true
            }.copy()
        } finally {
            _uiModelLiveData.value = currentUIModel.apply {
                isLoading = false
            }.copy()
        }
    }

    fun onSwipeRefresh() = onLoad()
}