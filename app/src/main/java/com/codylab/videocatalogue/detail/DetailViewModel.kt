package com.codylab.videocatalogue.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.codylab.videocatalogue.core.livedata.Event
import com.codylab.videocatalogue.core.model.Item
import javax.inject.Inject

class DetailViewModel @Inject constructor(): ViewModel() {

    var uiModelLiveData = MutableLiveData<DetailUIModel>()

    fun setup(item: Item) {
        uiModelLiveData.value = DetailUIModel(item)
    }

    fun onClosedButtonClicked() {
        uiModelLiveData.value = DetailUIModel(closeEvent = Event(Unit))
    }
}