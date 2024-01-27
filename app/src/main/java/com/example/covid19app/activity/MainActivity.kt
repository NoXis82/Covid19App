package com.example.covid19app.activity

import android.os.Bundle
import android.util.Log
import com.example.covid19app.BR
import com.example.covid19app.R
import com.example.covid19app.databinding.ActivityMainBinding
import com.example.covid19app.viewmodel.MainActivityVM
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>() {

    private var binding: ActivityMainBinding? = null
    private var progressDialog: ProgressHelper? = null

    @Inject
    lateinit var mainActivityVM: MainActivityVM
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainActivityVM {
        return mainActivityVM
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressHelper(this)
        binding = getViewDataBinding()
        mainActivityVM.getCovidData()

        mainActivityVM.loadingStatus.setObserve(this) { aBoolean ->
            if (aBoolean!!) {
                showLoading()
            } else {
                progressDialog?.dismissDialog()
            }
        }

        mainActivityVM.response.observe(this) { apiResponseWrapper ->
            if (apiResponseWrapper.data != null) {
                Log.d(TAG, "do something with data(records)")
            }
        }
    }
    private fun showLoading() {
        progressDialog?.showDialog()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}