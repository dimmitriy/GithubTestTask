package com.github.test.repository

import com.github.test.entity.response.SearchUserEntity

interface UserRepository {

    fun selectUser(user: SearchUserEntity)

    fun getUser(): SearchUserEntity

}