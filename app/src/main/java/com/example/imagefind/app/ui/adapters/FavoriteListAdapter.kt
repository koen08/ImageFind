package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagefind.R
import com.example.imagefind.domain.models.ImageDto
import com.example.imagefind.domain.models.ImageFavoriteDto

class FavoriteListAdapter(private val imageList: List<ImageFavoriteDto>) :
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {
    var importantListener: ((ImageDto) -> Unit)? = { }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageItemGrid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.favorite_list_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageDao = imageList[position]
        Glide.with(holder.imageView).load(imageDao.url).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}