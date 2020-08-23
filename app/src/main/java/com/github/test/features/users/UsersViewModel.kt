package com.github.test.features.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.test.base.BaseActivityViewModel
import com.github.test.entity.response.SearchUserEntity
import com.github.test.repository.SearchRepository
import com.github.test.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersViewModel @Inject constructor(private val searchRepo: SearchRepository,
                                         private val userRepo: UserRepository
): BaseActivityViewModel() {

    private val perPage = 20
    private var currentPage = 0
    private var totalCount: Int = 0
    private val _searchUsers = MutableLiveData<List<SearchUserEntity>>()
    private val navigation = MutableLiveData<Any>()
    private val disposable = CompositeDisposable()

    fun needLoadMore() = totalCount - currentPage * perPage > perPage

    fun searchUsers(query: String) {
        showProgress.value = true
        Timber.d("searchUser currentPage: %d, perPage: %d", currentPage, perPage)
        searchRepo.searchUsers(query, currentPage, perPage)
            .subscribe({
                if (totalCount == 0) {
                    totalCount = it.totalCount
                }
                currentPage++
                _searchUsers.value = it.items
                showProgress.value = false
            }, {
                it.printStackTrace()
                showProgress.value = false
            })
            .let(disposable::add)
    }

    fun onUserClicked(user: SearchUserEntity) {
        userRepo.selectUser(user)
        navigation.value = Any()
    }

    fun onCloseClicked() {
        _searchUsers.value = listOf()
    }

    fun getSearchedUsers(): LiveData<List<SearchUserEntity>> = _searchUsers

    fun getNavigation(): LiveData<Any> = navigation

}