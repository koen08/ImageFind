package com.example.imagefind.data.models

import com.google.gson.annotations.SerializedName

data class ImageDaoNet(
    val id: Long,
    @SerializedName("webformatURL") val url: String
)
