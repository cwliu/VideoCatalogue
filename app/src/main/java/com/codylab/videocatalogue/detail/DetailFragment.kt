package com.codylab.videocatalogue.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.core.extension.loadImageFromUrl
import com.codylab.videocatalogue.core.model.Item
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    companion object {
        fun newInstance(item: Item): DetailFragment {
            return DetailFragment().apply {
                val bundle = Bundle().apply {
                    putParcelable(ITEM_KEY, item)
                }
                arguments = bundle
            }
        }

        private const val ITEM_KEY = "item_key"
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

        val item = arguments?.getParcelable<Item>(ITEM_KEY)

        name.text = item?.title
        item?.images?.landscape?.let {
            image.loadImageFromUrl(it)
        }
        year.text = item?.year?.toString()
        description.text = item?.description

        closeButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}
