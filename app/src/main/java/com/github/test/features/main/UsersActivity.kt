package com.github.test.features.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.SearchUserEntity
import com.github.test.extensions.initWithAdapter
import com.github.test.features.main.adapter.UserClickListener
import com.github.test.features.main.adapter.UsersAdapter
import com.github.test.features.repos.UserDetailsActivity
import com.github.test.view.ExtendedTextWatcher
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : BaseActivity<UsersViewModel>(), UserClickListener {

    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        initViews()
        viewModel.getSearchedUsers().observe(::displayUsers)
        viewModel.getNavigation().observe(::navigateTo)
        viewModel.showProgress.observe(::showProgress)
    }

    private fun initViews() {
        search_users_close.setOnClickListener { search_users.setText("") }
        adapter = UsersAdapter(this)
        search_list.initWithAdapter(adapter)
        search_users.addTextChangedListener(object : ExtendedTextWatcher() {
            override fun afterTextChanged(p0: Editable) {
                if (p0.toString().isNotEmpty()) {
                    viewModel.searchUsers(p0.toString())
                } else {
                    viewModel.onCloseClicked()
                }
            }
        })
    }

    private fun navigateTo(any: Any) {
        startActivity(Intent(this, UserDetailsActivity::class.java))
    }

    private fun displayUsers(users: List<SearchUserEntity>) {
        adapter.setItems(users)
    }

    private fun showProgress(isShowProgress: Boolean) {
        search_progress.visibility = if (isShowProgress) View.VISIBLE else View.GONE
    }

    override fun onUserClicked(user: SearchUserEntity) {
        viewModel.onUserClicked(user)
    }

}