package com.github.test.features.main

import android.os.Bundle
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.SearchUserEntity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.searchUsers("tom").observe(::displayUsers)
    }

    private fun displayUsers(users: List<SearchUserEntity>) {

    }

}