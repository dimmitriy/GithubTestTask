package com.github.test.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import javax.inject.Inject

abstract class BaseActivity<VM : BaseActivityViewModel<V>, V: MxView> : BaseInjectorActivity() {

    @Inject lateinit var viewModel: VM

    protected inline fun <reified T> LiveData<T>.observe(noinline block: (t: T) -> Unit) {
        observe(this@BaseActivity, Observer<T>(block))
    }

}