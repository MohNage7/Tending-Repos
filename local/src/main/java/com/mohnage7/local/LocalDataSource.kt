package com.mohnage7.local

import com.mohnage7.local.db.entity.TrendingReposEntity
import io.reactivex.rxjava3.core.Single

interface LocalDataSource {
    fun getTrendingRepos(): Single<List<TrendingReposEntity>>
    fun insertAll(reposList: List<TrendingReposEntity>)
}