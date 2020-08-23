package com.github.test.repository

import com.github.test.api.GitHubApiService
import com.github.test.api.RxObservableSchedulers
import com.github.test.api.RxSingleSchedulers
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.entity.response.UserRepoEntity
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: GitHubApiService,
                                             private val rxSingleSchedulers: RxSingleSchedulers,
                                             private val rxObservableSchedulers: RxObservableSchedulers): UserRepository {

    private lateinit var user: UserDetailsResponse

    override fun selectUser(user: UserDetailsResponse) {
        this.user = user
    }

    override fun getUserDetails(): Observable<UserDetailsResponse> =
        apiService.getUserDetails(user.login)
            .compose(rxObservableSchedulers.applySchedulers())

    override fun getUserDetails(name: String): Observable<UserDetailsResponse> =
        apiService.getUserDetails(name)
            .compose(rxObservableSchedulers.applySchedulers())

    override fun getUserRepos(): Single<List<UserRepoEntity>> =
        apiService.getUserRepos(user.login)
            .compose(rxSingleSchedulers.applySchedulers())

}