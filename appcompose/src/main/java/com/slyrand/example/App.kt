package com.slyrand.example

import android.app.Application
import com.slyrand.data.di.datasourceModule
import com.slyrand.data.di.networkModule
import com.slyrand.data.di.repositoryModule
import com.slyrand.example.di.userModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    datasourceModule,
                    repositoryModule,
                    userModule
                )
            )
        }
    }
}