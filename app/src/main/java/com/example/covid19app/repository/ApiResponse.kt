package com.example.covid19app.repository

import android.util.Log
import android.util.MalformedJsonException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class ApiResponse<T>() {
    var errorCode = "200"
    var errorDescription = "Something went wrong"
    var data: T? = null
    var status: Int? = null

    constructor(status: Int, data: T?, throwable: Throwable?) : this() {
        this.data = data
        this.status = status
        if (throwable != null) {
            parseException(throwable)
        }
    }

    private fun parseException(throwable: Throwable) {
        when (throwable) {
            is SocketTimeoutException -> {
                this.errorDescription =
                    "Ooops! We couldn't capture your request in time. Please try again"
                this.errorCode = "no_internet"
            }

            is MalformedJsonException -> {
                this.errorDescription = "Ooops! We hit an error. Please try again later"
                this.errorCode = "malformed_error"
            }

            is IOException -> {
                this.errorDescription =
                    "Ooops! Not connected or cellular data network. Please connect and try again"
                this.errorCode = "no_internet"
            }

            is HttpException -> {
                if (throwable.code() == 500) {
                    this.errorDescription = "Internet server error"
                    this.errorCode = throwable.response()?.code().toString()
                } else {
                    try {
                        this.errorDescription = throwable.response()?.errorBody().toString()
                        Log.d(TAG, "errorDescription: $errorDescription")
                        this.errorCode = throwable.response()?.code().toString()
                        Log.d(TAG, "errorCode: $errorCode")
                    } catch (e: IOException) {
                        Log.e(TAG, "parseException: ", e)
                        e.stackTrace
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "ApiResponse"
    }
}