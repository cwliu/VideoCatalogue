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
import com.codylab.videocatalogue.main.DetailFragmentNavigator
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
        viewModel.setup()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCatalogueRecyclerView()
        observeUI()
    }

    private fun observeUI() {
        viewModel.uiModelLiveData.observeNonNull(this) { uiModel ->
            catalogueAdapter.categories.clear()
            catalogueAdapter.categories.addAll(uiModel.categories)
            catalogueAdapter.notifyDataSetChanged()
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
