package com.github.test.api

import com.github.test.entity.response.SearchUsersResponse
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.entity.response.UserRepoEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Single<SearchUsersResponse>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Single<List<UserRepoEntity>>

    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): Single<UserDetailsResponse>

}