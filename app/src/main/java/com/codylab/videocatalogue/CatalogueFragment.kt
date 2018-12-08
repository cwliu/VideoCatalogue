package com.codylab.videocatalogue


import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codylab.videocatalogue.core.extension.getViewModel
import com.codylab.videocatalogue.core.extension.observeNonNull
import com.codylab.videocatalogue.core.extension.showToast
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CatalogueFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CatalogueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel(viewModelFactory)
        viewModel.setup()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiModelLiveData.observeNonNull(this) { uiModel ->
            this@CatalogueFragment.context?.showToast(uiModel.categories[0].category)
        }
    }
}
