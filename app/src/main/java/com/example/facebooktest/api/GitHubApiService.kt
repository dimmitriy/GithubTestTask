package com.example.facebooktest.api

import com.example.facebooktest.entity.response.SearchUsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Single<SearchUsersResponse>

}