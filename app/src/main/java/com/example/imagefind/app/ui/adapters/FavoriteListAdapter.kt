package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imagefind.R
import com.example.imagefind.app.ui.adapters.listeners.AbstractListener
import com.example.imagefind.app.ui.adapters.listeners.FavoriteListener
import com.example.imagefind.app.ui.adapters.listeners.ListenerClick
import com.example.imagefind.databinding.FavoriteListItemBinding
import com.example.imagefind.domain.models.ImageFavorite
import kotlin.math.abs

class FavoriteListAdapter() :
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {
    var imageList: List<ImageFavorite> = emptyList()
        set(newList) {
            val diffCallBack = FavoriteDiffCallBack(field, newList)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            diffResult.dispatchUpdatesTo(this)
            field = newList
        }
    var favoriteListener: FavoriteListener = FavoriteListener()


    class ViewHolder(itemView: View) :
        AbstractViewHolder<ImageFavorite>(itemView), ListenerClick<ImageFavorite> {
        private val binding by viewBinding(FavoriteListItemBinding::bind)

        override fun bind(anyObject: ImageFavorite) {
            with(binding) {
                Glide.with(imageItemGrid).load(anyObject.url).into(imageItemGrid)
            }

        }

        override fun onClick(
            item: ImageFavorite,
            abstractListener: AbstractListener<ImageFavorite>
        ) {
            with(binding) {
                imageItemGrid.setOnLongClickListener {
                    (abstractListener as FavoriteListener).invokeDeleteImageListener(item)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.favorite_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageFavorite = imageList[position]
        imageFavorite.let { holder.bind(it) }
        holder.onClick(imageFavorite, favoriteListener)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}