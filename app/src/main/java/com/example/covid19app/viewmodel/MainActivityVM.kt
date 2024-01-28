package com.example.covid19app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.covid19app.models.ItemInfo
import com.example.covid19app.repository.ApiResponse
import com.example.covid19app.repository.DataManager
import com.example.covid19app.repository.ResponseListener
import javax.inject.Inject

class MainActivityVM @Inject constructor(
    private val dataManager: DataManager
) : BaseViewModel(dataManager) {

    val loadingStatus: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val response = MutableLiveData<ApiResponse<List<ItemInfo>>>()
    fun getCovidData() {
        dataManager.getCovidData(object : ResponseListener<List<ItemInfo>> {
            override fun onStart() {
                Log.d(TAG, "onStart")
                loadingStatus.setNewValue(true)
            }

            override fun onFinish() {
                Log.d(TAG, "onFinish")
                loadingStatus.setNewValue(false)
            }

            override fun onResponse(apiResponse: ApiResponse<List<ItemInfo>>) {
                Log.d(TAG, "onResponse: ${apiResponse.data}")
                loadingStatus.setNewValue(false)
                if (apiResponse.data != null) {
                    response.value = apiResponse
                }
            }

        })
    }

    companion object {
        private const val TAG = "MainActivityVM"
    }
}