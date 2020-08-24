package com.github.test.features.userdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.entity.response.UserRepoEntity
import com.github.test.extensions.initWithAdapter
import com.github.test.features.userdetails.adapter.RepoClickListener
import com.github.test.features.userdetails.adapter.ReposAdapter
import com.github.test.view.ExtendedTextWatcher
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*
import org.joda.time.format.DateTimeFormat

class UserDetailsActivity : BaseActivity<UserDetailsViewModel>(), RepoClickListener {

    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        initViews()
        viewModel.getUserDetails().observe(::displayUserInfo)
        viewModel.getUserRepos().observe(::displayUserRepos)
        viewModel.showProgress.observe(::showProgress)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        search_repos_close.setOnClickListener { search_repos.setText("") }
        adapter = ReposAdapter(this)
        search_repos.addTextChangedListener(object : ExtendedTextWatcher() {
            override fun afterTextChanged(p0: Editable) {
                viewModel.filterRepos(p0.toString())
            }
        })
        search_repos_list.initWithAdapter(adapter)
    }

    private fun displayUserInfo(user: UserDetailsResponse) {
        Picasso.get()
            .load(user.avatarUrl)
            .into(user_details_image)
        val noData = getString(R.string.no_data)
        user_details_name.text = String.format("%s %s", getString(R.string.user_details_name_title), user.name ?: noData)
        user_details_email.text = String.format("%s %s", getString(R.string.user_details_email_title), user.email ?: noData)
        user_details_location.text = String.format("%s %s", getString(R.string.user_details_location_title), user.location ?: noData)
        user_details_followers.text = String.format("%s %s", getString(R.string.user_details_followers_title), user.followers ?: noData)
        user_details_following.text = String.format("%s %s", getString(R.string.user_details_following_title), user.following ?: noData)
        user_details_biography.text = String.format("%s %s", getString(R.string.user_details_description), user.bio ?: noData)

        val date = user.joinDate?.let { DateTimeFormat.forPattern("dd.MM.yyyy").print(it) } ?: let { noData }
        user_details_join_date.text = String.format("%s %s", getString(R.string.user_details_join_date_title), date)
    }

    private fun displayUserRepos(repos: List<UserRepoEntity>) {
        adapter.setItems(repos)
        search_user_repos_no_results.visibility = if (repos.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun showProgress(isShowProgress: Boolean) {
        filter_repo_progress.visibility = if (isShowProgress) View.VISIBLE else View.GONE
    }

    override fun onRepoClicked(repo: UserRepoEntity) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(repo.htmlUrl)
        startActivity(intent)
    }

}