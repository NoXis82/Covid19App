package com.example.covid19app.repository

import com.example.covid19app.BaseScheduler
import javax.inject.Inject

class DataManager() {
    private var api: Api? = null
    private var baseScheduler: BaseScheduler? = null

    @Inject
    constructor(api: Api, baseScheduler: BaseScheduler) : this() {
        this.api = api
        this.baseScheduler = baseScheduler
    }

}