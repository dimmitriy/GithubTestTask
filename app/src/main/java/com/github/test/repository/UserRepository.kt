package com.github.test.repository

import com.github.test.entity.response.UserDetailsResponse
import com.github.test.entity.response.UserRepoEntity
import io.reactivex.Observable
import io.reactivex.Single

interface UserRepository {

    fun selectUser(user: UserDetailsResponse)

    fun getUserDetails(): Observable<UserDetailsResponse>

    fun getUserDetails(name: String): Observable<UserDetailsResponse>

    fun getUserRepos(): Single<List<UserRepoEntity>>

}