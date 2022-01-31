package com.example.demoandroidapp

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class RNBasicModule internal constructor(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
        override fun getName(): String {
            return this::class.java.simpleName
        }

        @ReactMethod
        fun sendValueToNative(value: String?) {
            currentActivity?.let {
                val intent = Intent(it, MainActivity::class.java)
                intent.putExtra(MainActivity.VALUE_KEY, value)
                it.startActivity(intent)
            }
        }

        @ReactMethod
        fun finishActivity() {
            currentActivity?.finish()
        }
}