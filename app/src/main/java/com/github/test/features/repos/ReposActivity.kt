package com.github.test.features.repos

import android.os.Bundle
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.UserRepoEntity

class ReposActivity : BaseActivity<ReposViewModel, ReposView>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        viewModel.getUserRepos("tom").observe(::displayUserInfo)
    }

    private fun displayUserInfo(users: List<UserRepoEntity>) {

    }

}