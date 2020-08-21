package com.github.test.features.repos

import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.SearchUserEntity
import com.github.test.entity.response.UserRepoEntity
import com.github.test.extensions.initWithAdapter
import com.github.test.features.repos.adapter.RepoClickListener
import com.github.test.features.repos.adapter.ReposAdapter
import com.github.test.view.ExtendedTextWatcher
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repos.*

class ReposActivity : BaseActivity<ReposViewModel>(), RepoClickListener {

    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initViews()
        viewModel.getUser().observe(::displayUserInfo)
        viewModel.showProgress.observe(::showProgress)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        search_repos_close.setOnClickListener { search_repos.setText("") }
        adapter = ReposAdapter(this)
        search_repos.addTextChangedListener(object : ExtendedTextWatcher() {
            override fun afterTextChanged(p0: Editable) {
                viewModel.filterRepos(p0.toString())
            }
        })
        search_repos_list.initWithAdapter(adapter)
    }

    private fun displayUserInfo(user: SearchUserEntity) {
        Picasso.with(this)
            .load(user.avatarUrl)
            .into(user_details_image)
        user_details_name.text = String.format("%s %s", getString(R.string.user_details_name_title), user.login)
        // TODO how to find necessary user data
        viewModel.obtainUserRepos(user.login).observe(::displayUserRepos)
    }

    private fun displayUserRepos(repos: List<UserRepoEntity>) {
        adapter.setItems(repos)
    }

    private fun showProgress(isShowProgress: Boolean) {
        filter_repo_progress.visibility = if (isShowProgress) View.VISIBLE else View.GONE
    }

    override fun onRepoClicked(repo: UserRepoEntity) {

    }

}