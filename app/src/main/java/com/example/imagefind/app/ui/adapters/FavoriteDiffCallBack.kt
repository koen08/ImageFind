package com.example.imagefind.app.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.models.ImageFavorite

class FavoriteDiffCallBack(
) : DiffUtil.ItemCallback<ImageFavorite>() {
    override fun areItemsTheSame(oldItem: ImageFavorite, newItem: ImageFavorite): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageFavorite, newItem: ImageFavorite): Boolean {
        return oldItem == newItem
    }
}