package com.mohnage7.tendingrepos

data class TrendingRepo(
    val image: Int,
    val author: String,
    val name: String,
    val description: String,
    val language: String? = null,
    val stars: Int
)