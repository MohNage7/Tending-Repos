package com.mohnage7.domain.model

data class TrendingRepo(
    val image: String,
    val author: String,
    val name: String,
    val description: String,
    val language: String? = null,
    val stars: Int
)