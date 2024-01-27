package com.example.covid19app.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.covid19app.models.ResponseWrapper
import com.example.covid19app.repository.ApiResponse
import com.example.covid19app.repository.DataManager
import com.example.covid19app.repository.ResponseListener
import javax.inject.Inject

class MainActivityVM @Inject constructor(
    private val dataManager: DataManager
) : BaseViewModel(dataManager) {

    val loadingStatus: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val response = MutableLiveData<ApiResponse<ResponseWrapper>>()
    fun getCovidData() {
        dataManager.getCovidData(object : ResponseListener<ResponseWrapper> {
            override fun onStart() {
                loadingStatus.setNewValue(true)
            }

            override fun onFinish() {
                loadingStatus.setNewValue(false)
            }

            override fun onResponse(apiResponse: ApiResponse<ResponseWrapper>) {
                loadingStatus.setNewValue(false)
                if (apiResponse.data != null) {
                    response.value = apiResponse
                }
            }

        })
    }
}