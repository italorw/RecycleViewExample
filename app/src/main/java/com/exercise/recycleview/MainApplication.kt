package com.exercise.recycleview

import android.app.Application
import com.bugsnag.android.Bugsnag

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Bugsnag.start(this)
        Bugsnag.notify(RuntimeException("Test error"))
    }
}
