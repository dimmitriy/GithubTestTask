package com.example.facebooktest.repository

import com.example.facebooktest.entity.response.SearchUsersResponse
import com.example.facebooktest.entity.response.UserRepoEntity
import io.reactivex.Single

interface SearchRepository {

    fun searchUsers(query: String): Single<SearchUsersResponse>

    fun getUserRepos(username: String): Single<List<UserRepoEntity>>

}