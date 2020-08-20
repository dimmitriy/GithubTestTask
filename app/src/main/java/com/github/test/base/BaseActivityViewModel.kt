package com.github.test.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseActivityViewModel: ViewModel() {

    val showProgress = MutableLiveData<Boolean>()

}
