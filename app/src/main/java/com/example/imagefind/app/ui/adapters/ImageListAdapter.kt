package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagefind.domain.models.ImageDao
import com.example.imagefind.R
import com.google.android.material.imageview.ShapeableImageView

class ImageListAdapter(private val imageList: List<ImageDao>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageItem)
        val nickNameTextView: TextView = view.findViewById(R.id.textNickName)
        val avatarImageVew: ShapeableImageView = view.findViewById(R.id.imageAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageDao = imageList[position]
        holder.nickNameTextView.text = imageDao.userName
        Glide.with(holder.avatarImageVew).load(imageDao.avatar).into(holder.avatarImageVew);
        Glide.with(holder.imageView).load(imageDao.url).into(holder.imageView);
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}