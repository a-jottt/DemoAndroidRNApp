package com.example.mylibrary

interface MyRNSDKListener {

    fun sendValueToNative(value: String?)
    fun finishRNActivity()
}