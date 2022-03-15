package com.example.imagefind.app.ui.adapters.viewholders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imagefind.R
import com.example.imagefind.app.ui.adapters.AbstractViewHolder
import com.example.imagefind.app.ui.adapters.listeners.ImageListenerArgs
import com.example.imagefind.app.ui.adapters.listeners.Listener
import com.example.imagefind.app.ui.adapters.listeners.ListenerClick
import com.example.imagefind.databinding.ListImageItemBinding
import com.example.imagefind.domain.models.Image

class ImageListViewHolder(itemView: View) :
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

    override fun onClick(item: Image,  vararg listeners: Listener) {
        with(binding) {
            importantImageView.setOnClickListener {
                importantImageView.setColorFilter(importantImageView.resources.getColor(R.color.black))
                (listeners[0] as ImageListenerArgs).invoke(item)
            }
        }
    }

}