package com.codylab.videocatalogue.catalogue

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.core.extension.loadImageFromUrl
import com.codylab.videocatalogue.core.model.Item
import kotlinx.android.synthetic.main.item_video.view.*

enum class ImageOrientation {
    Portrait, Landscape
}

class ItemListAdapter(
    private val context: Context,
    val imageOrientation: ImageOrientation = ImageOrientation.Portrait
) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

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
            itemView.title.text = item.title

            val imageUrl = if (imageOrientation == ImageOrientation.Portrait) {
                itemView.image.layoutParams.width =
                        view.resources.getDimension(R.dimen.cover_image_portrait_width).toInt()
                itemView.title.layoutParams.width =
                        view.resources.getDimension(R.dimen.cover_image_portrait_width).toInt()
                itemView.image.layoutParams.height =
                        view.resources.getDimension(R.dimen.cover_image_portrait_height).toInt()

                item.images.portrait
            } else {
                itemView.image.layoutParams.width =
                        view.resources.getDimension(R.dimen.cover_image_landscape_width).toInt()
                itemView.title.layoutParams.width =
                        view.resources.getDimension(R.dimen.cover_image_landscape_width).toInt()
                itemView.image.layoutParams.height =
                        view.resources.getDimension(R.dimen.cover_image_landscape_height).toInt()

                item.images.landscape
            }

            itemView.image.loadImageFromUrl(
                imageUrl,
                view.resources.getDimension(R.dimen.image_radius).toInt()
            )

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}
