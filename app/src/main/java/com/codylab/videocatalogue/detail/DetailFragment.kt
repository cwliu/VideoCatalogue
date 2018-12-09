package com.codylab.videocatalogue.detail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.core.extension.getViewModel
import com.codylab.videocatalogue.core.extension.loadImageFromUrl
import com.codylab.videocatalogue.core.model.Item
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*

import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    companion object {
        private const val ITEM_KEY = "item_key"

        fun newInstance(item: Item): DetailFragment {
            return DetailFragment().apply {
                val bundle = Bundle().apply {
                    putParcelable(ITEM_KEY, item)
                }
                arguments = bundle
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel(viewModelFactory)
        val item = arguments?.getParcelable<Item>(ITEM_KEY)
        item?.let {
            viewModel.onLoad(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closeButton.setOnClickListener {
            viewModel.onClosedButtonClicked()
        }

        viewModel.uiModelLiveData.observe(this, Observer<DetailUIModel> { model ->
            model?.item?.let {
                name.text = it.title

                val imageUrl = if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
                    it.images.landscape
                } else {
                    it.images.portrait
                }
                image.loadImageFromUrl(imageUrl)
                year.text = it.year.toString()
                description.text = it.description
            }

            model?.closeEvent?.getDataIfNotHandled()?.let {
                close()
            }
        })
    }

    private fun close() {
        activity?.supportFragmentManager?.popBackStack()
    }
}
