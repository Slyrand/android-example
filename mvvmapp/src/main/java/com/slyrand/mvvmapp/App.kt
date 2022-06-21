package com.slyrand.mvvmapp

import android.app.Application
import com.slyrand.data.BuildConfig
import com.slyrand.data.di.datasourceModule
import com.slyrand.data.di.networkModule
import com.slyrand.data.di.repositoryModule
import com.slyrand.mvvmapp.user.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MvvmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MvvmApp)
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