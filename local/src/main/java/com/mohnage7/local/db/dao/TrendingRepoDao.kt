package com.mohnage7.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohnage7.local.db.entity.TrendingReposEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface TrendingRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(trendingReposEntityList: List<TrendingReposEntity>)

    @Query("SELECT * from repo")
    fun getAll(): Single<List<TrendingReposEntity>>
}
