package com.example.covid19app.repository

import com.example.covid19app.models.ItemInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("/v1")
    fun getCovidData(): Observable<List<ItemInfo>>
}