package com.mohnage7.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohnage7.local.db.dao.TrendingRepoDao
import com.mohnage7.local.db.entity.TrendingReposEntity

const val DATA_BASE_NAME = "trending_repo_db"

@Database(entities = [TrendingReposEntity::class], version = 1, exportSchema = false)
abstract class TrendingRepoDatabase : RoomDatabase() {
    abstract val trendingRepoDao: TrendingRepoDao
}