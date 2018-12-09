package com.codylab.videocatalogue.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.codylab.videocatalogue.core.livedata.Event
import com.codylab.videocatalogue.core.model.Item
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    var uiModelLiveData = MutableLiveData<DetailUIModel>()
    val currentUIModel = DetailUIModel()

    fun onLoad(item: Item) {
        uiModelLiveData.value = currentUIModel.apply {
            this.item = item
        }.copy()
    }

    fun onClosedButtonClicked() {
        uiModelLiveData.value = currentUIModel.apply {
            closeEvent = Event(Unit)
        }.copy()
    }
}