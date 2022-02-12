package com.example.imagefind.app

import android.app.Application
import com.example.imagefind.app.di.AppComponent
import com.example.imagefind.app.di.DaggerAppComponent
import com.example.imagefind.app.di.DataBaseModule

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .dataBaseModule(DataBaseModule(this))
            .build()
    }
}