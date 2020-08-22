package com.github.test.repository

import com.github.test.api.GitHubApiService
import com.github.test.api.RxSingleSchedulers
import com.github.test.entity.response.SearchUserEntity
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.entity.response.UserRepoEntity
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: GitHubApiService,
                                             private val rxSingleSchedulers: RxSingleSchedulers): UserRepository {

    private lateinit var user: SearchUserEntity

    override fun selectUser(user: SearchUserEntity) {
        this.user = user
    }

    override fun getUserDetails(): Single<UserDetailsResponse> =
        apiService.getUserDetails(user.login)
            .compose(rxSingleSchedulers.applySchedulers())

    override fun getUserRepos(): Single<List<UserRepoEntity>> =
        apiService.getUserRepos(user.login)
            .compose(rxSingleSchedulers.applySchedulers())

}