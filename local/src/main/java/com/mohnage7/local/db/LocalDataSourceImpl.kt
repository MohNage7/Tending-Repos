package com.mohnage7.local.db

import com.mohnage7.local.LocalDataSource
import com.mohnage7.local.db.dao.TrendingRepoDao
import com.mohnage7.local.db.entity.TrendingReposEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: TrendingRepoDao) :
    LocalDataSource {

    override fun insertAll(reposList: List<TrendingReposEntity>) {
        dao.insertAll(reposList)
    }

    override fun getTrendingRepos(): Single<List<TrendingReposEntity>> {
        return dao.getAll()
    }
}