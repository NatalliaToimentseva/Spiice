package com.example.spiice

import android.app.Application
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.roomDB.DataBaseProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesRepository.init(this)
        DataBaseProvider.init(this)
    }
}