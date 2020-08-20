package com.github.test.features.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.test.base.BaseActivityViewModel
import com.github.test.entity.response.UserRepoEntity
import com.github.test.repository.SearchRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: SearchRepository): BaseActivityViewModel() {

    private val _userRepos = MutableLiveData<List<UserRepoEntity>>()
    private val disposable = CompositeDisposable()

    fun getUserRepos(username: String): LiveData<List<UserRepoEntity>> {
        repository.getUserRepos(username)
            .subscribe({
                _userRepos.value = it
            }, {
                it.printStackTrace()
            })
            .let(disposable::add)
        return _userRepos
    }

}