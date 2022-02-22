package com.example.imagefind.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable.TABLE_NAME
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.imagefind.data.database.models.ImageTable

@Database(entities = [ImageTable::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDaoDatabase
}