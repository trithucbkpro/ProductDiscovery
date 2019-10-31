package com.teko.proddiscovery.app

import android.app.Application
import com.teko.proddiscovery.BuildConfig
import timber.log.Timber

class MainApplication : Application() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent.inject(this)
    }
}