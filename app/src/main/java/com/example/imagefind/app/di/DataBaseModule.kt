package com.example.imagefind.app.di

import android.content.Context
import com.example.imagefind.data.database.AppDatabase
import com.example.imagefind.data.database.ImageDaoDatabase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideImageDaoDatabase(context: Context): ImageDaoDatabase {
        val appDatabase = AppDatabase.getAppDataBase(context)
        return appDatabase!!.imageDao()
    }
}