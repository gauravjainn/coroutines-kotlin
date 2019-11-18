package com.app.coroutines_kotlin

import android.app.Application
import com.app.coroutines_kotlin.config.remoteModule
import com.app.coroutines_kotlin.config.repositoryModule
import com.app.coroutines_kotlin.config.uiModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.startKoin


open class App : Application() {

/*    Kotlin’s Coroutines allow the use of suspend functions,
    Channels and Flows and they all operate in the context of a so-called CoroutineScope.*/

    private val appModules by lazy {
        listOf(remoteModule, repositoryModule, uiModule)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModules)

        Fresco.initialize(this)
    }

}