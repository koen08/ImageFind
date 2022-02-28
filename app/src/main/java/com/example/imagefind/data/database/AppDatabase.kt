package com.example.imagefind.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imagefind.data.database.models.ImageTable

@Database(entities = [ImageTable::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDaoDatabase
}