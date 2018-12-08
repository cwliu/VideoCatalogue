package com.codylab.videocatalogue.core.extension

import android.annotation.SuppressLint
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
@SuppressLint("CheckResult")
fun View.onDebounceClick(action: (Any) -> Unit) {
    RxView.clicks(this)
        .debounce(500, TimeUnit.MILLISECONDS)
        .subscribe(action, { ignored -> })
}