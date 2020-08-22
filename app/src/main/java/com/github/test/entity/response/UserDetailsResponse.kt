package com.github.test.entity.response

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class UserDetailsResponse(@SerializedName("login") var login: String,
                               @SerializedName("id") var id: Int,
                               @SerializedName("node_id") var nodeId: String,
                               @SerializedName("avatar_url") var avatarUrl: String,
                               @SerializedName("name") var name: String?,
                               @SerializedName("location")val location: String?,
                               @SerializedName("email")val email: String?,
                               @SerializedName("bio")val bio: String?,
                               @SerializedName("created_at")val joinDate: DateTime?,
                               @SerializedName("followers")val followers: Int?,
                               @SerializedName("following")val following: Int?,
                               @SerializedName("public_repos")val publicRepos: Int?)