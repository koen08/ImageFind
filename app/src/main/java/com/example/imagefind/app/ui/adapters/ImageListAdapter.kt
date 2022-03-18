package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.R
import com.example.imagefind.app.ui.adapters.viewholders.HeaderSearchViewHolder
import com.example.imagefind.app.ui.adapters.viewholders.ImageListViewHolder
import com.example.imagefind.domain.models.Image


class ImageListAdapter(
    val listenerItemImage: ((Image) -> Unit) = {},
    var listenerSearch: ((String) -> Unit) = {},
    var listenerAdvanceSearch: (() -> Unit) = {}
) :
    PagingDataAdapter<Image, RecyclerView.ViewHolder>(ImageListDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> HeaderSearchViewHolder(
                layoutInflater.inflate(
                    R.layout.header_search,
                    parent,
                    false
                )
            )
            TYPE_ITEM -> ImageListViewHolder(
                layoutInflater.inflate(
                    R.layout.list_image_item,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) TYPE_HEADER else TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0 && this.snapshot().items[0].page == 1
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageListViewHolder) {
            val positionItem = position - 1
            if (positionItem != -1) {
                val item = getItem(positionItem)
                item?.let { holder.bind(it) }
                holder.onClick(item!!, listenerItemImage)
            }

        }
        if (holder is HeaderSearchViewHolder) {
            holder.onClick(listenerAdvanceSearch)
            holder.onClick(listenerSearch)
        }
    }

}