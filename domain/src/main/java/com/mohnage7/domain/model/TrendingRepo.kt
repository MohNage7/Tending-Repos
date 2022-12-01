package com.mohnage7.domain.model

data class TrendingRepo(
    val id: Long,
    val image: String,
    val author: String,
    val name: String,
    val description: String,
    val language: String? = null,
    val stars: Int
)