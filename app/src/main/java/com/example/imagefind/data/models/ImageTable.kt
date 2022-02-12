package com.example.imagefind.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageTable(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val url: String
)