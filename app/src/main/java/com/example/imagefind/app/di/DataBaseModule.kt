package com.example.imagefind.app.di

import android.content.Context
import androidx.room.Room
import com.example.imagefind.data.database.AppDatabase
import com.example.imagefind.data.database.ImageDaoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideImageDaoDatabase(appDatabase: AppDatabase): ImageDaoDatabase {
        return appDatabase.imageDao()
    }

    @Provides
    @Singleton
    fun provideRoom(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "image_data_base"
        ).fallbackToDestructiveMigration().build()
    }
}