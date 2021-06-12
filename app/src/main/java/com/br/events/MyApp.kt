package com.br.events

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.events.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModules)
        }
    }
}