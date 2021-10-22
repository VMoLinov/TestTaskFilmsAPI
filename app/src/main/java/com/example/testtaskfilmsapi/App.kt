package com.example.testtaskfilmsapi

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigatorHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}