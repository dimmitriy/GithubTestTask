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
                                         userRepo: UserRepository
): BaseActivityViewModel() {

    private var userReposList: List<UserRepoEntity> = listOf()
    private val userReposData = MutableLiveData<List<UserRepoEntity>>()
    private val userData = MutableLiveData<SearchUserEntity>()
    private val disposable = CompositeDisposable()

    init {
        userData.value = userRepo.getUser()
    }

    fun getUser() = userData

    fun obtainUserRepos(username: String): LiveData<List<UserRepoEntity>> {
        showProgress.value = true
        repository.getUserRepos(username)
            .subscribe({
                userReposList = it
                userReposData.value = it
                showProgress.value = false
            }, {
                it.printStackTrace()
                showProgress.value = false
            })
            .let(disposable::add)
        return userReposData
    }

    fun filterRepos(query: String) {
        userReposList.filter { it.name.contains(query) }.let {
            userReposData.value = it
        }
    }

}