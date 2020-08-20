package com.github.test.repository

import com.github.test.api.GitHubApiService
import com.github.test.api.RxSingleSchedulers
import com.github.test.entity.response.SearchUsersResponse
import com.github.test.entity.response.UserRepoEntity
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService: GitHubApiService,
                                               private val rxSingleSchedulers: RxSingleSchedulers): SearchRepository {

    override fun searchUsers(query: String): Single<SearchUsersResponse> =
        apiService.searchUsers(query)
            .compose(rxSingleSchedulers.applySchedulers())

    override fun getUserRepos(username: String): Single<List<UserRepoEntity>> =
        apiService.getUserRepos(username)
            .compose(rxSingleSchedulers.applySchedulers())

}