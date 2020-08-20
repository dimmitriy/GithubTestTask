package com.github.test.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.test.base.BaseActivityViewModel
import com.github.test.entity.response.SearchUserEntity
import com.github.test.repository.SearchRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: SearchRepository): BaseActivityViewModel() {

    private val _searchUsers = MutableLiveData<List<SearchUserEntity>>()
    private val disposable = CompositeDisposable()

    fun searchUsers(query: String): LiveData<List<SearchUserEntity>> {
        repository.searchUsers(query)
            .subscribe({
                _searchUsers.value = it.items
            }, {
                it.printStackTrace()
            })
            .let(disposable::add)
        return _searchUsers
    }

}