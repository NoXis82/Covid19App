package com.example.covid19app.activity

import android.os.Bundle
import com.example.covid19app.BR
import com.example.covid19app.R
import com.example.covid19app.databinding.ActivityMainBinding
import com.example.covid19app.viewmodel.MainActivityVM
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>() {
    private var binding: ActivityMainBinding? = null

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
        binding = getViewDataBinding()
    }
}