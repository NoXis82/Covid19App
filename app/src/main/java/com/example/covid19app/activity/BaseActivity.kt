package com.example.covid19app.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    private var viewDataBinding: T? = null
    private var viewModel: V? = null

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInject()
        performDataBinding()
    }

    fun getViewDataBinding(): T? {
        return viewDataBinding
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewModel = if (viewModel == null) getViewModel() else viewModel
        viewDataBinding?.setVariable(getBindingVariable(), viewModel)
        viewDataBinding?.lifecycleOwner = this
        viewDataBinding?.executePendingBindings()
    }

    private fun performDependencyInject() {
        AndroidInjection.inject(this)
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}