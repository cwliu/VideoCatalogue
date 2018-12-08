package com.codylab.videocatalogue.core.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    this.context?.let {
        Toast.makeText(it, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}
