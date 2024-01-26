package com.example.covid19app.di

import android.app.Application
import com.example.covid19app.CovidApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(covidApp: CovidApp)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}