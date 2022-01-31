package com.example.mylibrary

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class RNBasicModule internal constructor(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return this::class.java.simpleName
    }

    @ReactMethod
    fun sendValueToNative(value: String?) {
        MyRNSDK.listener?.sendValueToNative(value)
    }

    @ReactMethod
    fun finishActivity() {
        MyRNSDK.listener?.finishRNActivity()
    }
}