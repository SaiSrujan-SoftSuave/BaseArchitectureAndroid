package com.saibabui.baseapplicationstructure.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.saibabui.baseapplicationstructure.CoinApplication

class Preference(application: Application) {
    private val tokenKey = "tokenKey"
    private val preferenceName = "bustleSpot"
    private val bearer = ""
    private val preference: SharedPreferences =
        application.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    var token: String?
        get() = preference.getString(tokenKey, bearer)
        set(value) = preference.edit().putString(tokenKey, value).apply()
}