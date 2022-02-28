package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagefind.databinding.ListImageItemBinding
import com.example.imagefind.domain.models.Image
import com.google.android.material.imageview.ShapeableImageView

class ImageListAdapter :
    PagingDataAdapter<Image, ImageListAdapter.ViewHolder>(ImageListDiffUtils()) {
    var importantListener: ((Image) -> Unit)? = { }

    class ViewHolder(listImageItemBinding: ListImageItemBinding) :
        RecyclerView.ViewHolder(listImageItemBinding.root) {
        val imageView: ImageView = listImageItemBinding.imageItem
        val nickNameTextView: TextView = listImageItemBinding.textNickName
        val avatarImageVew: ShapeableImageView = listImageItemBinding.imageAvatar
        val textViews: TextView = listImageItemBinding.textViews
        val textLikes: TextView = listImageItemBinding.textLikes
        val importantImageView: ImageView = listImageItemBinding.importantImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = ListImageItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageDao = getItem(position)
        holder.nickNameTextView.text = imageDao!!.userName
        val textLikes = imageDao.likes.toString() + " like"
        val textViews = imageDao.view.toString() + " views"
        holder.textLikes.text = textLikes
        holder.textViews.text = textViews
        Glide.with(holder.avatarImageVew).load(imageDao.avatar).into(holder.avatarImageVew)
        Glide.with(holder.imageView).load(imageDao.url).into(holder.imageView)

        holder.importantImageView.setOnClickListener {
            importantListener?.invoke(imageDao)
        }
    }
}