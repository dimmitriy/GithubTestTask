package com.github.test.features.main

import android.os.Bundle
import android.text.Editable
import android.view.View
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.SearchUserEntity
import com.github.test.extensions.initWithAdapter
import com.github.test.features.main.adapter.UsersAdapter
import com.github.test.view.ExtendedTextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = UsersAdapter()
        search_list.initWithAdapter(adapter)
        search_users.addTextChangedListener(object: ExtendedTextWatcher() {
            override fun afterTextChanged(p0: Editable) {
                viewModel.searchUsers(p0.toString())
            }
        })
        viewModel.getSearchedUsers().observe(::displayUsers)
        viewModel.showProgress.observe(::showProgress)
    }

    private fun displayUsers(users: List<SearchUserEntity>) {
        adapter.setItems(users)
    }

    private fun showProgress(isShowProgress: Boolean) {
        search_progress.visibility = if (isShowProgress) View.VISIBLE else View.GONE
    }

}