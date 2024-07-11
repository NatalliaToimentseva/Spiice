package com.example.spiice

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        SharedPreferencesRepository.init(this)
//        DataBaseModule.init(this)
    }
}