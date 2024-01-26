package com.example.covid19app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.covid19app.repository.DataManager

open class BaseViewModel() : ViewModel() {
    private var dataManager: DataManager? = null

    constructor(dataManager: DataManager) : this() {
        this.dataManager = dataManager
    }

    override fun onCleared() {
        super.onCleared()
    }


}