package com.github.test.repository

import com.github.test.api.GitHubApiService
import com.github.test.api.RxObservableSchedulers
import com.github.test.entity.response.SearchUsersResponse
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService: GitHubApiService,
                                               private val rxObservableSchedulers: RxObservableSchedulers
): SearchRepository {

    override fun searchUsers(query: String, page: Int, perPage: Int): Observable<SearchUsersResponse> =
        apiService.searchUsers(query, page, perPage)
            .compose(rxObservableSchedulers.applySchedulers())

}