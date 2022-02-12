package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagefind.R
import com.example.imagefind.domain.models.ImageDto
import com.google.android.material.imageview.ShapeableImageView

class ImageListAdapter(private val imageList: List<ImageDto>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageItem)
        val nickNameTextView: TextView = view.findViewById(R.id.textNickName)
        val avatarImageVew: ShapeableImageView = view.findViewById(R.id.imageAvatar)
        val textViews: TextView = view.findViewById(R.id.textViews)
        val textLikes: TextView = view.findViewById(R.id.textLikes)
        val importantImageView: ImageView = view.findViewById(R.id.importantImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_image_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageDao = imageList[position]
        holder.nickNameTextView.text = imageDao.userName
        val textLikes = imageDao.likes.toString() + " likes"
        val textViews = imageDao.view.toString() + " views"
        holder.textLikes.text = textLikes
        holder.textViews.text = textViews
        Glide.with(holder.avatarImageVew).load(imageDao.avatar).into(holder.avatarImageVew)
        Glide.with(holder.imageView).load(imageDao.url).into(holder.imageView)

        holder.importantImageView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}