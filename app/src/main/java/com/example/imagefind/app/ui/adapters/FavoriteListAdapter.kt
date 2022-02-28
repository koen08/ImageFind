package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagefind.databinding.FavoriteListItemBinding
import com.example.imagefind.domain.models.ImageFavorite

class FavoriteListAdapter() :
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {
    var imageList: List<ImageFavorite> = emptyList()
        set(newList) {
            val diffCallBack = FavoriteDiffCallBack(field, newList)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            diffResult.dispatchUpdatesTo(this)
            field = newList
        }
    var importantListener: ((ImageFavorite) -> Unit)? = { }


    class ViewHolder(favoriteListItemBinding: FavoriteListItemBinding) :
        RecyclerView.ViewHolder(favoriteListItemBinding.root) {
        val imageView: ImageView = favoriteListItemBinding.imageItemGrid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = FavoriteListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageFavorite = imageList[position]
        Glide.with(holder.imageView).load(imageFavorite.url).into(holder.imageView)
        holder.imageView.setOnLongClickListener {
            importantListener?.invoke(imageFavorite)
            true
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}