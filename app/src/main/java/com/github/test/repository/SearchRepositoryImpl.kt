package com.github.test.repository

import com.github.test.api.GitHubApiService
import com.github.test.api.RxSingleSchedulers
import com.github.test.entity.response.SearchUsersResponse
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService: GitHubApiService,
                                               private val rxSingleSchedulers: RxSingleSchedulers): SearchRepository {

    override fun searchUsers(query: String, page: Int, perPage: Int): Single<SearchUsersResponse> =
        apiService.searchUsers(query, page, perPage)
            .compose(rxSingleSchedulers.applySchedulers())

}