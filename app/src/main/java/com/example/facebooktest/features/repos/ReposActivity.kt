package com.example.facebooktest.features.repos

import android.os.Bundle
import com.example.facebooktest.R
import com.example.facebooktest.base.BaseActivity
import com.example.facebooktest.entity.response.UserRepoEntity

class ReposActivity : BaseActivity<ReposViewModel, ReposView>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        viewModel.getUserRepos("tom").observe(::displayUserInfo)
    }

    private fun displayUserInfo(users: List<UserRepoEntity>) {

    }

}