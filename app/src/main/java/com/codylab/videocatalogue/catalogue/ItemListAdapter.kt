package com.codylab.videocatalogue.catalogue

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.core.extension.loadImageFromUrl
import com.codylab.videocatalogue.core.extension.onDebounceClick
import com.codylab.videocatalogue.core.model.Item
import kotlinx.android.synthetic.main.item_video.view.*

class ItemListAdapter(private val context: Context) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    val items = mutableListOf<Item>()

    var onItemClickListener: ((Item) -> Unit)? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            // TODO portrait or landscape
            // TODO placeholder image
            // TODO dimen
            itemView.title.text = item.title
            itemView.image.loadImageFromUrl(
                item.images.portrait,
                view.resources.getDimension(R.dimen.image_radius).toInt()
            )

            itemView.onDebounceClick {
                onItemClickListener?.invoke(item)
            }
        }
    }
}
