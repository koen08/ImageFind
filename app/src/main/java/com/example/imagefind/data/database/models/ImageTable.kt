package com.example.imagefind.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageTable(
    @PrimaryKey val imageId: Long,
    val imageUrl: String
)