package com.saibabui.baseapplicationstructure

import android.app.Application
import com.saibabui.baseapplicationstructure.common.NetworkManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoinApplication : Application(){
    private val networkManager:NetworkManager = NetworkManager
    override fun onCreate() {
        super.onCreate()
        networkManager.create(this)
    }
}