package com.example.covid19app.di

import com.example.covid19app.viewmodel.MainActivityVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    abstract fun bindMainActivityVM(mainActivityVM: MainActivityVM): MainActivityVM
}