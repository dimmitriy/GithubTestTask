package com.github.test.features.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.test.base.BaseActivityViewModel
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.entity.response.UserRepoEntity
import com.github.test.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val repository: UserRepository
): BaseActivityViewModel() {

    private val userDetailsData = MutableLiveData<UserDetailsResponse>()
    private var userReposList: List<UserRepoEntity> = listOf()
    private val userReposData = MutableLiveData<List<UserRepoEntity>>()
    private val disposable = CompositeDisposable()

    fun getUserDetails(): LiveData<UserDetailsResponse> {
        showProgress.value = true
        repository.getUserDetails()
            .subscribe({
                userDetailsData.value = it
                showProgress.value = false
            }, {
                it.printStackTrace()
                showProgress.value = false
            })
            .let(disposable::add)
        return userDetailsData
    }

    fun getUserRepos(): LiveData<List<UserRepoEntity>> {
        showProgress.value = true
        repository.getUserRepos()
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