package com.github.test.entity.response

import com.google.gson.annotations.SerializedName

data class SearchUsersResponse(@SerializedName("total_count") var totalCount: Int,
                               @SerializedName("incomplete_results") var incompleteResults: Boolean,
                               @SerializedName("items") var items: List<SearchUserEntity>)