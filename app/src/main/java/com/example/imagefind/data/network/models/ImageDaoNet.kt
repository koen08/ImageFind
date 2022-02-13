package com.example.imagefind.data.network.models

import com.google.gson.annotations.SerializedName

data class ImageDaoNet(
    val id: Long,
    @SerializedName("webformatURL") val url: String,
    val userImageURL: String,
    @SerializedName("user") val userName: String,
    val likes: Int,
    val views: Int
)
