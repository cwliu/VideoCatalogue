package com.codylab.videocatalogue.catalogue

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.core.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CatalogueAdapter(val context: Context) : RecyclerView.Adapter<CatalogueAdapter.ViewHolder>() {

    val categories = mutableListOf<Category>()

    override fun getItemCount(): Int = categories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(categories[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            val adapter = ItemListAdapter(view.context)
            adapter.items.addAll(category.items)

            itemView.categoryName.text = category.category
            itemView.items.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.items.adapter = adapter
        }
    }
}
