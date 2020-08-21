package com.github.test.features.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.test.base.BaseActivityViewModel
import com.github.test.entity.response.SearchUserEntity
import com.github.test.entity.response.UserRepoEntity
import com.github.test.repository.SearchRepository
import com.github.test.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: SearchRepository,
                                         private val userRepo: UserRepository
): BaseActivityViewModel() {

    private val _userRepos = MutableLiveData<List<UserRepoEntity>>()
    private val _user = MutableLiveData<SearchUserEntity>()
    private val disposable = CompositeDisposable()

    init {
        _user.value = userRepo.getUser()
    }

    fun getUser() = _user

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