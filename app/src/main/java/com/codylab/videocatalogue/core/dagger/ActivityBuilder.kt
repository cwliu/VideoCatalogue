package com.codylab.videocatalogue.core.dagger

import com.codylab.videocatalogue.CatalogueFragment
import com.codylab.videocatalogue.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity


    @ContributesAndroidInjector
    abstract fun bindCatalogueFragment(): CatalogueFragment
}