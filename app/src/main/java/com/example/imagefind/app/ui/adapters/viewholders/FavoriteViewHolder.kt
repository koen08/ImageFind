package com.example.imagefind.app.ui.adapters.viewholders

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imagefind.app.ui.adapters.AbstractViewHolder
import com.example.imagefind.databinding.FavoriteListItemBinding
import com.example.imagefind.domain.models.ImageFavorite

class FavoriteViewHolder(itemView: View) :
    AbstractViewHolder<ImageFavorite>(itemView) {
    private val binding by viewBinding(FavoriteListItemBinding::bind)

    override fun bind(anyObject: ImageFavorite) {
        with(binding) {
            Glide.with(imageItemGrid).load(anyObject.url).into(imageItemGrid)
        }

    }

    override fun onClick(
        item: ImageFavorite,
        vararg listener: ((ImageFavorite) -> Unit)
    ) {
        with(binding) {
            imageItemGrid.setOnLongClickListener {
                listener[0].invoke(item)
                true
            }
        }
    }
}