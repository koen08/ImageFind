package com.example.imagefind.domain.models

data class ImageDao(
    val id: Long,
    val url: String,
    val avatar: String,
    val userName: String,
    val likes: Int,
    val view: Int
)
