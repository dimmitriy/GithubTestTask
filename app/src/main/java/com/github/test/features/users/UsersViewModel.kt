package com.github.test.features.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.test.base.BaseActivityViewModel
import com.github.test.entity.response.UserDetailsResponse
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
    private val _searchUsers = MutableLiveData<List<UserDetailsResponse>>()
    private val navigation = MutableLiveData<Any>()
    private val disposable = CompositeDisposable()

    fun needLoadMore() = totalCount - currentPage * perPage > perPage

    fun searchUsers(query: String) {
        showProgress.value = true
        searchRepo.searchUsers(query, currentPage, perPage)
            .map {
                Timber.d("searchUser currentPage: %d, totalCount: %d, query: %s", currentPage, it.totalCount, query)
                if (totalCount == 0) {
                    totalCount = it.totalCount
                }
                currentPage++
                it
            }
            .flatMapIterable {
                Timber.d("flatMapIterable size: ${it.items.size}")
                it.items
            }
            .flatMap { searchUserEntity ->
                Timber.d("flatMap getUserDetails login: ${searchUserEntity.login}")
                userRepo.getUserDetails(searchUserEntity.login)
            }
            .buffer(20)
            .subscribe({
                Timber.d("searchUser onNext size: ${it.size}")
                _searchUsers.value = it
            }, {
                Timber.d("searchUser onError: ${it.message}")
                it.printStackTrace()
            }, {
                Timber.d("searchUser onComplete")
                showProgress.value = false
            })
            .let(disposable::add)
    }

    fun onUserClicked(user: UserDetailsResponse) {
        userRepo.selectUser(user)
        navigation.value = Any()
    }

    fun onCloseClicked() {
        disposable.clear()
        totalCount = 0
        currentPage = 0
        _searchUsers.value = listOf()
    }

    fun getSearchedUsers(): LiveData<List<UserDetailsResponse>> = _searchUsers

    fun getNavigation(): LiveData<Any> = navigation

}