package com.example.facebooktest.repository

import com.example.facebooktest.api.GitHubApiService
import com.example.facebooktest.api.RxSingleSchedulers
import com.example.facebooktest.entity.response.SearchUsersResponse
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService: GitHubApiService,
                                               private val rxSingleSchedulers: RxSingleSchedulers): SearchRepository {

    override fun searchUsers(query: String): Single<SearchUsersResponse> =
        apiService.searchUsers(query)
            .compose(rxSingleSchedulers.applySchedulers())

}