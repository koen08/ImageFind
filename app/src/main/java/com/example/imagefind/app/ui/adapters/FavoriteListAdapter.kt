package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.imagefind.R
import com.example.imagefind.app.ui.adapters.viewholders.FavoriteViewHolder
import com.example.imagefind.domain.models.ImageFavorite

class FavoriteListAdapter(
    var listenerAdvanceSearch: ((ImageFavorite) -> Unit) = {}
) :
    PagingDataAdapter<ImageFavorite, FavoriteViewHolder>(FavoriteDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.favorite_list_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val imageFavorite = getItem(position)
        imageFavorite.let { holder.bind(it!!) }
        holder.onClick(imageFavorite!!, listenerAdvanceSearch)
    }

}