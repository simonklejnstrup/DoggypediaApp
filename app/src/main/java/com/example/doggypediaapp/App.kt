package com.example.doggypediaapp

import android.app.Application
import com.example.doggypediaapp.sharedprefs.SharedPrefs
import dagger.hilt.android.HiltAndroidApp

val sharedPrefs: SharedPrefs by lazy {
    App.sharedPrefs!!
}

class App: Application() {

    companion object {
        var sharedPrefs: SharedPrefs? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        sharedPrefs = SharedPrefs(applicationContext)
    }
}