package com.mohnage7.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo")
data class TrendingReposEntity(
    @PrimaryKey val id: Long,
    val image: String,
    val author: String,
    val name: String,
    val description: String,
    val language: String? = null,
    val stars: Int
)