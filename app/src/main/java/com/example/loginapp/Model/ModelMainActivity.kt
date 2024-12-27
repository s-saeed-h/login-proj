package com.example.loginapp.Model

import android.content.Context
import com.example.loginapp.AndroidWrapper.deviceInfo

class ModelMainActivity(private val context: Context) {
    fun getId()=deviceInfo.getAndroidId(context)
}