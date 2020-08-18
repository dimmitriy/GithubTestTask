package com.example.facebooktest.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.facebooktest.base.BaseActivityViewModel
import com.example.facebooktest.entity.response.SearchUserEntity
import com.example.facebooktest.repository.SearchRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: SearchRepository): BaseActivityViewModel<MainView>() {

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