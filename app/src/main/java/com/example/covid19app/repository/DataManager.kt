package com.example.covid19app.repository

import com.example.covid19app.BaseScheduler
import com.example.covid19app.models.ResponseWrapper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DataManager() {
    private var api: Api? = null
    private var scheduler: BaseScheduler? = null

    @Inject
    constructor(api: Api, baseScheduler: BaseScheduler) : this() {
        this.api = api
        this.scheduler = baseScheduler
    }

    private fun <T> performRequest(
        observable: Observable<T>,
        responseListener: ResponseListener<T>
    ) {
        observable.subscribeOn(scheduler?.io())
            .observeOn(scheduler?.ui())
            .subscribe(object : Observer<T> {

                override fun onSubscribe(d: Disposable) {
                    responseListener.onStart()
                }

                override fun onError(e: Throwable) {
                    responseListener.onResponse(
                        ApiResponse(ResponseStatus.ERROR.ordinal, null, e)
                    )
                }

                override fun onComplete() {
                    responseListener.onFinish()
                }

                override fun onNext(t: T & Any) {
                    responseListener.onResponse(
                        ApiResponse(ResponseStatus.SUCCESS.ordinal, t, null)
                    )
                }
            })
    }

    fun getCovidData(responseListener: ResponseListener<ResponseWrapper>) {
        performRequest(api!!.getCovidData(), responseListener)
    }
}