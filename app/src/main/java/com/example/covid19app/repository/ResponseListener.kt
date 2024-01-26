package com.example.covid19app.repository

interface ResponseListener<T> {
    fun onStart()
    fun onResponse(apiResponse: ApiResponse<T>)
    fun onFinish()
}