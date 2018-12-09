package com.codylab.videocatalogue.catalogue


import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.core.extension.getViewModel
import com.codylab.videocatalogue.core.extension.observeNonNull
import com.codylab.videocatalogue.core.extension.setVisibility
import com.codylab.videocatalogue.core.extension.showToast
import com.codylab.videocatalogue.detail.DetailFragmentNavigator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_catalogue.*
import javax.inject.Inject


class CatalogueFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CatalogueViewModel

    private lateinit var catalogueAdapter: CatalogueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel(viewModelFactory)
        viewModel.onLoad()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUIEvents()
        setUpCatalogueRecyclerView()
        observeUI()
    }

    private fun setUpUIEvents() {
        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = false
            viewModel.onSwipeRefresh()
        }
    }

    private fun observeUI() {
        viewModel.uiModelLiveData.observeNonNull(this) { uiModel ->

            uiModel.categories?.let {
                catalogueAdapter.categories.clear()
                catalogueAdapter.categories.addAll(it)
                catalogueAdapter.notifyDataSetChanged()
            }

            progressBar.setVisibility(uiModel.isLoading)
            errorView.setVisibility(uiModel.hasError)
            uiModel.message?.getDataIfNotHandled()?.let {
                this@CatalogueFragment.showToast(it)
            }
        }
    }

    private fun setUpCatalogueRecyclerView() {
        context?.let {
            catalogueAdapter = CatalogueAdapter(it)
            catalogueAdapter.onItemClickListener = { item ->
                (activity as DetailFragmentNavigator).openDetailFragment(item)
            }
            catalogueRecyclerView.adapter = catalogueAdapter
            catalogueRecyclerView.layoutManager = LinearLayoutManager(it)
        }
    }
}
