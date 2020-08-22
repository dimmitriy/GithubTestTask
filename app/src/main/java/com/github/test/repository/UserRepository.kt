package com.github.test.repository

import com.github.test.entity.response.SearchUserEntity
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.entity.response.UserRepoEntity
import io.reactivex.Single

interface UserRepository {

    fun selectUser(user: SearchUserEntity)

    fun getUserDetails(): Single<UserDetailsResponse>

    fun getUserRepos(): Single<List<UserRepoEntity>>

}