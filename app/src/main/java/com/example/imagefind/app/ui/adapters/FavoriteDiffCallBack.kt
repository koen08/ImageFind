package com.example.imagefind.app.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.imagefind.domain.models.ImageFavorite

class FavoriteDiffCallBack(
    private val oldList: List<ImageFavorite>,
    private val newList: List<ImageFavorite>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = oldList[oldItemPosition]
        val newFavorite = newList[newItemPosition]
        return oldFavorite.id == newFavorite.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = oldList[oldItemPosition]
        val newFavorite = newList[newItemPosition]
        return oldFavorite == newFavorite
    }
}