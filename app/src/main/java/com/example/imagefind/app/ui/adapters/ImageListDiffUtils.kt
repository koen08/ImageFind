package com.example.imagefind.app.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.imagefind.domain.models.Image

class ImageListDiffUtils : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}