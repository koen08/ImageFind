package com.example.imagefind.data.network.models

import com.google.gson.annotations.SerializedName

data class ImageNet(
    @SerializedName("id") val id: Long,
    @SerializedName("webformatURL") val url: String,
    @SerializedName("userImageURL") val userImageURL: String,
    @SerializedName("user") val userName: String,
    @SerializedName("likes") val likes: Int,
    @SerializedName("views") val views: Int
)
