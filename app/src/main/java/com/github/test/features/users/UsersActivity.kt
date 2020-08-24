package com.github.test.features.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.test.R
import com.github.test.base.BaseActivity
import com.github.test.entity.response.SearchUserEntity
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.extensions.initWithAdapter
import com.github.test.features.users.adapter.UserClickListener
import com.github.test.features.users.adapter.UsersAdapter
import com.github.test.features.userdetails.UserDetailsActivity
import com.github.test.view.RecyclerScrollListener
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_users.*
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class UsersActivity : BaseActivity<UsersViewModel>(), UserClickListener {

    private lateinit var scrollListener: RecyclerScrollListener
    private lateinit var searchQuery: String
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
        adapter = UsersAdapter()
        adapter.setOnClickListener(this)
        search_list.initWithAdapter(adapter)

        scrollListener = object : RecyclerScrollListener(search_list.layoutManager as LinearLayoutManager) {
            override fun onLoadMore() {
                if (viewModel.needLoadMore()) {
                    viewModel.searchUsers(searchQuery)
                }
            }
        }
        scrollListener.setVisibleThreshold(10)
        search_list.addOnScrollListener(scrollListener)

        RxTextView.textChanges(search_users)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ query ->
                searchQuery = query.toString()
                adapter.cleanItems()
                if (query.isNotEmpty()) {
                    viewModel.searchUsers(query.toString())
                } else {
                    viewModel.onCloseClicked()
                }
            }
    }

    private fun navigateTo(any: Any) {
        startActivity(Intent(this, UserDetailsActivity::class.java))
    }

    private fun displayUsers(users: List<UserDetailsResponse>) {
        Timber.d("displayUsers size: ${users.size}")
        scrollListener.setLoading(false)
        if (users.isEmpty()) {
            adapter.cleanItems()
        } else {
            adapter.addItems(users)
        }
        search_users_no_results.visibility = if (users.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun showProgress(isShowProgress: Boolean) {
        search_progress.visibility = if (isShowProgress) View.VISIBLE else View.GONE
    }

    override fun onUserClicked(user: UserDetailsResponse) {
        viewModel.onUserClicked(user)
    }

}