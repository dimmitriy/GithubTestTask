package com.github.test.repository

import com.github.test.entity.response.SearchUserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {

    private lateinit var user: SearchUserEntity

    override fun selectUser(user: SearchUserEntity) {
        this.user = user
    }

    override fun getUser(): SearchUserEntity = user

}