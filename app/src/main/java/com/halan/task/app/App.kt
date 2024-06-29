package com.halan.task.app
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.StrictMode
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var instance: App
            private set

    }




    override fun onCreate() {
        super.onCreate()
        instance = this

        //baseViewModel.setConnectivityManger(connectivityManger)
        //connectivityManger.startMonitoring()
    }




    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.e("app_attach","1")
        Log.e("app","1")
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e("app_change","1")
    }



}
