package com.example.imagefind.app.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.imagefind.data.network.models.ImageNet
import com.example.imagefind.domain.models.Image

class ImageListDiffUtils : DiffUtil.ItemCallback<ImageNet>() {
    override fun areItemsTheSame(oldItem: ImageNet, newItem: ImageNet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageNet, newItem: ImageNet): Boolean {
        return oldItem == newItem
    }
}