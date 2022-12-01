package com.mohnage7.remote.model

import com.google.gson.annotations.SerializedName

data class TrendingRepoResponse(
    @SerializedName("items") val items: List<TrendingRepoRemote>
) {
    data class TrendingRepoRemote(
        @SerializedName("id")
        val id: Long,
        @SerializedName("owner")
        val owner: Owner,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("language")
        val language: String? = null,
        @SerializedName("stargazers_count")
        val stars: Int,
    )

    class Owner(
        @SerializedName("login")
        val name: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
    )
}


