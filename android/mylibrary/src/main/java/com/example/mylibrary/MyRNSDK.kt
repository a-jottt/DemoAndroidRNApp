package com.example.mylibrary

import android.content.Context

class MyRNSDK {

    companion object {

        var listener: MyRNSDKListener? = null

        fun init(myRNSDKListener: MyRNSDKListener) {
            listener = myRNSDKListener
        }
    }
}