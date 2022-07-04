package com.shubham.okhttpissues.views

interface CallBackListener {
    fun onStarted()
    fun onSuccess()
    fun onFailed(message: String)
}