package com.example.covid19app.di

import com.example.covid19app.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}