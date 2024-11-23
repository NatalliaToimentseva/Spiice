package com.example.spiice

import android.app.Application
import com.example.spiice.di.AppComponent
import com.example.spiice.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().provideContext(applicationContext).build()
    }

    companion object {

        var appComponent: AppComponent? = null
    }
}