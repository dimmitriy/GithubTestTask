package com.github.test.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.test.base.BaseActivityViewModel
import com.github.test.entity.response.SearchUserEntity
import com.github.test.repository.SearchRepository
import com.github.test.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val searchRepo: SearchRepository,
                                        private val userRepo: UserRepository
): BaseActivityViewModel() {

    private val _searchUsers = MutableLiveData<List<SearchUserEntity>>()
    private val navigation = MutableLiveData<Any>()
    private val disposable = CompositeDisposable()

    fun searchUsers(query: String) {
        showProgress.value = true
        searchRepo.searchUsers(query)
            .subscribe({
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

    fun getSearchedUsers(): LiveData<List<SearchUserEntity>> = _searchUsers

    fun getNavigation(): LiveData<Any> = navigation

}