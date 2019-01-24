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

    private val uiModel = CatalogueUIModel()

    private val _uiModelLiveData = MutableLiveData<CatalogueUIModel>()
    val uiModelLiveData: LiveData<CatalogueUIModel>
        get() = _uiModelLiveData

    private suspend fun updateUiModel(block: suspend (CatalogueUIModel) -> Unit) {
        block.invoke(uiModel)
        _uiModelLiveData.value = uiModel.copy()
    }

    fun onLoad() = launch {
        updateUiModel {
            it.isLoading = true
            it.hasError = false
        }

        try {
            updateUiModel {
                it.categories = catalogueRepository.getCategories()
            }
        } catch (t: Throwable) {
            updateUiModel {
                it.message = Event(t.toString())
                it.hasError = true
            }
        } finally {
            updateUiModel {
                it.isLoading = false
            }
        }
    }

    fun onSwipeRefresh() = onLoad()
}