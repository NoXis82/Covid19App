package com.example.covid19app

import io.reactivex.Scheduler

interface BaseScheduler {
    fun io(): Scheduler
    fun ui(): Scheduler
}