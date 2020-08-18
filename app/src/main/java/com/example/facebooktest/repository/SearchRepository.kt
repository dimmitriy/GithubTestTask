package com.example.facebooktest.repository

import com.example.facebooktest.entity.response.SearchUsersResponse
import io.reactivex.Single

interface SearchRepository {

    fun searchUsers(query: String): Single<SearchUsersResponse>

}