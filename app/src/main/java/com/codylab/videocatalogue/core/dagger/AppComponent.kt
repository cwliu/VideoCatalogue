package com.codylab.videocatalogue.core.dagger

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: Application)
}