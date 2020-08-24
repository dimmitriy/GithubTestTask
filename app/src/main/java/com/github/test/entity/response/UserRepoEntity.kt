package com.github.test.entity.response

import com.google.gson.annotations.SerializedName

data class UserRepoEntity(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("html_url") var htmlUrl: String,
    @SerializedName("url") var url: String,
    @SerializedName("stargazers_count") var stargazersCount: Int,
    @SerializedName("forks_count") var forksCount: Int)