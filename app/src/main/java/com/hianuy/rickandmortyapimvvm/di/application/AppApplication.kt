package com.hianuy.rickandmortyapimvvm.di.application

import android.app.Application
import com.hianuy.rickandmortyapimvvm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(appModule)
        }
    }
}