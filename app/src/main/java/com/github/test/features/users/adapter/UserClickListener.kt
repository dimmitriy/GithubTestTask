package com.github.test.features.users.adapter

import com.github.test.entity.response.SearchUserEntity

interface UserClickListener {

    fun onUserClicked(user: SearchUserEntity)

}