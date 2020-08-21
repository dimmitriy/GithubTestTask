package com.github.test.features.repos

import android.os.Bundle
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.SearchUserEntity
import com.github.test.entity.response.UserRepoEntity
import com.github.test.extensions.initWithAdapter
import com.github.test.features.repos.adapter.RepoClickListener
import com.github.test.features.repos.adapter.ReposAdapter
import kotlinx.android.synthetic.main.activity_repos.*

class ReposActivity : BaseActivity<ReposViewModel>(), RepoClickListener {

    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        adapter = ReposAdapter(this)
        search_repos_list.initWithAdapter(adapter)
        viewModel.getUser().observe(::displayUserInfo)
    }

    private fun displayUserInfo(user: SearchUserEntity) {
        viewModel.getUserRepos(user.login).observe(::displayUserRepos)
    }

    private fun displayUserRepos(repos: List<UserRepoEntity>) {
        adapter.setItems(repos)
    }

    override fun onRepoClicked(repo: UserRepoEntity) {

    }

}