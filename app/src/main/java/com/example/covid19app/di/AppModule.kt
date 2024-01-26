package com.example.covid19app.di

import android.app.Application
import android.content.Context
import com.example.covid19app.BaseScheduler
import com.example.covid19app.SchedulerProvider
import com.example.covid19app.repository.Api
import com.example.covid19app.repository.DataManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun getContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideScheduler(): BaseScheduler {
        return SchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideDataManager(api: Api, baseScheduler: BaseScheduler): DataManager {
        return DataManager(api, baseScheduler)
    }

}