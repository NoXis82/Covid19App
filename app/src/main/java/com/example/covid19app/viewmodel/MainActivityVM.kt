package com.example.covid19app.viewmodel

import com.example.covid19app.repository.DataManager
import javax.inject.Inject

class MainActivityVM @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    fun getCovidData() {

    }
}