package com.example.facebooktest.features.main

import android.os.Bundle
import com.example.facebooktest.R
import com.example.facebooktest.base.BaseActivity
import com.example.facebooktest.entity.response.SearchUserEntity

class MainActivity : BaseActivity<MainViewModel, MainView>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.searchUsers("tom").observe(::displayUsers)
    }

    private fun displayUsers(users: List<SearchUserEntity>) {

    }

}