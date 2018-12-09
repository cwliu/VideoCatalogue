package com.codylab.videocatalogue.core.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.showToast(message: String) {
    this.context?.let {
        Toast.makeText(it, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}
