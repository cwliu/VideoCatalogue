package com.codylab.videocatalogue.core.coroutine

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class ScopedViewModel(val dispatcher: DispatcherManager) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = job + dispatcher.main

    private val job = Job()

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}