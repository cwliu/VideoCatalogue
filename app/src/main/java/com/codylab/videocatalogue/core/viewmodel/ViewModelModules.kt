package com.codylab.foodie.core.dagger.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.codylab.videocatalogue.CatalogueViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CatalogueViewModel::class)
    abstract fun bindCatalogueViewModel(viewModel: CatalogueViewModel): ViewModel
}