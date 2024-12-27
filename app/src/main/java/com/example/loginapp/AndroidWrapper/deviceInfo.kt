package com.example.loginapp.AndroidWrapper

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object deviceInfo {
    private var androidId:String? = null
    @SuppressLint("HardwareIds")

    fun getAndroidId(context : Context):String{

        if(androidId == null){
            androidId = Settings.Secure.getString(
                context.contentResolver,Settings.Secure.ANDROID_ID
            )

        }
        return androidId ?: ""

    }
}