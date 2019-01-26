package com.codylab.videocatalogue.core.coroutine

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class ScopedViewModel<T>(private val dispatcher: DispatcherManager) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = job + dispatcher.main

    private val job = Job()

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }

    private val _uiModelLiveData = MutableLiveData<T>()
    val uiModelLiveData: LiveData<T> = _uiModelLiveData

    fun T.emit() {
        _uiModelLiveData.value = this
    }
}