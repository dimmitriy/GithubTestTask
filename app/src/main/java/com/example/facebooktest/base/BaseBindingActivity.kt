package com.example.facebooktest.base

import javax.inject.Inject

abstract class BaseActivity<VM : BaseActivityViewModel<V>, V: MxView> : BaseInjectorActivity() {

    @Inject lateinit var viewModel: VM

}