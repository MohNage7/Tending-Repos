package com.mohnage7.data

import com.mohnage7.domain.TrendingReposRepository
import com.mohnage7.domain.model.TrendingRepo
import com.mohnage7.local.LocalDataSource
import com.mohnage7.remote.RemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TrendingReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val refreshRateLimiter: RefreshLimiter
) : TrendingReposRepository {

//    private val refreshRateLimiter: RefreshRateLimiter<String> = RefreshRateLimiter()
    /**
     * Try to fetch the data from the database.
     * If it fails then try to fetch it from network
     */
    override fun getTrendingReposCacheFirst(): Single<List<TrendingRepo>> {
        return readFromCache()
            .doOnSuccess { shouldFetch(it) }
            .onErrorResumeWith(readFromRemote().doOnSuccess { synchronizeCacheWithRemote(it) })
    }

    private fun shouldFetch(trendingRepos: List<TrendingRepo>?) {
        if (trendingRepos.isNullOrEmpty() || refreshRateLimiter.shouldFetch(this::class.java.name)) throw CacheEmptyException()
    }

    /**
     * Try to fetch the data from the remote first.
     * Then cache the results and push the data up from the cache
     */
    override fun getTrendingReposRemoteFirst(): Single<List<TrendingRepo>> {
        return readFromRemote().doOnSuccess {
            synchronizeCacheWithRemote(it)
        }.flatMap {
            readFromCache()
        }
    }

    private fun synchronizeCacheWithRemote(list: List<TrendingRepo>) {
        localDataSource.insertAll(list.mapToTrendingRepoEntity())
    }

    private fun readFromRemote() = remoteDataSource.getTrendingRepos().mapResponseToTrendingRepo()

    private fun readFromCache() = localDataSource.getTrendingRepos().mapLocalToTrendingRepo()
}