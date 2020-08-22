package com.github.test.repository

import com.github.test.entity.response.SearchUsersResponse
import io.reactivex.Single

interface SearchRepository {

    fun searchUsers(query: String): Single<SearchUsersResponse>

}