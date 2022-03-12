package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imagefind.R
import com.example.imagefind.app.ui.adapters.listeners.AbstractListener
import com.example.imagefind.app.ui.adapters.listeners.ImageListener
import com.example.imagefind.app.ui.adapters.listeners.ImageListenerContract
import com.example.imagefind.app.ui.adapters.listeners.ListenerClick
import com.example.imagefind.databinding.ListImageItemBinding
import com.example.imagefind.domain.models.Image

class ImageListAdapter :
    PagingDataAdapter<Image, RecyclerView.ViewHolder>(ImageListDiffUtils()) {

    val imageListener: ImageListener = ImageListener()

    class ViewHolder(itemView: View) :
        AbstractViewHolder<Image>(itemView), ListenerClick<Image> {
        private val binding by viewBinding(ListImageItemBinding::bind)

        override fun bind(anyObject: Image) {
            with(binding) {
                this.textNickName.text = anyObject.userName
                val textLikes = anyObject.likes.toString() + " like"
                val textViews = anyObject.view.toString() + " views"
                this.textLikes.text = textLikes
                this.textViews.text = textViews
                Glide.with(imageAvatar).load(anyObject.avatar).into(imageAvatar)
                Glide.with(imageItem).load(anyObject.url).into(imageItem)
            }
        }

        override fun onClick(item: Image, abstractListener: AbstractListener<Image>) {
            with(binding) {
                importantImageView.setOnClickListener {
                    (abstractListener as ImageListenerContract).invokeImportantListener(item)
                }
            }
        }

    }

    class HeaderSearchViewHolder(itemView: View) :
        AbstractViewHolder<Image>(itemView), ListenerClick<Image> {
        private val binding by viewBinding(ListImageItemBinding::bind)

        override fun bind(anyObject: Image) {
            with(binding) {

            }
        }

        override fun onClick(item: Image, abstractListener: AbstractListener<Image>) {
            with(binding) {

            }
        }

    }

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
            TYPE_ITEM -> ViewHolder(layoutInflater.inflate(R.layout.list_image_item, parent, false))
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) TYPE_HEADER else TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    override fun getItemCount(): Int {
        return differ.itemCount + 1
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder) {
            val item = getItem(position)
            item?.let { holder.bind(it) }
            holder.onClick(item!!, imageListener)
        }
    }

}