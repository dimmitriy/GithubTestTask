package com.github.test.repository

import com.github.test.entity.response.SearchUsersResponse
import com.github.test.entity.response.UserRepoEntity
import io.reactivex.Single

interface SearchRepository {

    fun searchUsers(query: String): Single<SearchUsersResponse>

    fun getUserRepos(username: String): Single<List<UserRepoEntity>>

}