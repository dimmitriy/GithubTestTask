package com.github.test.entity.response

import com.google.gson.annotations.SerializedName

data class UserRepoOwnerEntity(
    @SerializedName("login") val login: String?,
    @SerializedName("id") val id: Int)