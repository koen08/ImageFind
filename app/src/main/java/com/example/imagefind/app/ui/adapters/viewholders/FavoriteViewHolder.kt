package com.example.imagefind.app.ui.adapters.viewholders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imagefind.app.ui.adapters.AbstractViewHolder
import com.example.imagefind.app.ui.adapters.listeners.FavoriteListenerArgs
import com.example.imagefind.app.ui.adapters.listeners.Listener
import com.example.imagefind.app.ui.adapters.listeners.ListenerClick
import com.example.imagefind.databinding.FavoriteListItemBinding
import com.example.imagefind.domain.models.ImageFavorite

class FavoriteViewHolder(itemView: View) :
    AbstractViewHolder<ImageFavorite>(itemView), ListenerClick<ImageFavorite> {
    private val binding by viewBinding(FavoriteListItemBinding::bind)

    override fun bind(anyObject: ImageFavorite) {
        with(binding) {
            Glide.with(imageItemGrid).load(anyObject.url).into(imageItemGrid)
        }

    }

    override fun onClick(
        item: ImageFavorite,
        vararg listeners: Listener
    ) {
        with(binding) {
            imageItemGrid.setOnLongClickListener {
                (listeners[0] as FavoriteListenerArgs).invoke(item)
                true
            }
        }
    }
}