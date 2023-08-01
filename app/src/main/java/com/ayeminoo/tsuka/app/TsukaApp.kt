package com.ayeminoo.tsuka.app

import android.app.Application
import com.ayeminoo.tsuka.BuildConfig
import timber.log.Timber

class TsukaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        Timber.e(BuildConfig.API_KEY)
    }

}