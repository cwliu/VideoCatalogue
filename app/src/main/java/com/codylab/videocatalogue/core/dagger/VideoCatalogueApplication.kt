package com.codylab.videocatalogue.core.dagger

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class VideoCatalogueApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().build()
    }
}