package com.uragiristereo.landmarks

import android.app.Application

class LandmarksApp : Application() {
    private var _appComponent: AndroidAppComponent? = null

    val appComponent: AndroidAppComponent; get() = _appComponent!!

    override fun onCreate() {
        super.onCreate()
        _appComponent = AndroidAppComponent::class.create(this)
    }
}
