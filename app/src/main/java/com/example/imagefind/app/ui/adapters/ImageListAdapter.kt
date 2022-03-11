package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
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
    PagingDataAdapter<Image, ImageListAdapter.ViewHolder>(ImageListDiffUtils()) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
        holder.onClick(item!!, imageListener)
    }

}