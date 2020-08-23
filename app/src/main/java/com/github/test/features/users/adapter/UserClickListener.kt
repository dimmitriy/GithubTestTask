package com.github.test.features.users.adapter

import com.github.test.entity.response.UserDetailsResponse

interface UserClickListener {

    fun onUserClicked(user: UserDetailsResponse)

}