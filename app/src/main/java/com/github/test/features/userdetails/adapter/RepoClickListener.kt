package com.github.test.features.userdetails.adapter

import com.github.test.entity.response.UserRepoEntity

interface RepoClickListener {

    fun onRepoClicked(repo: UserRepoEntity)

}