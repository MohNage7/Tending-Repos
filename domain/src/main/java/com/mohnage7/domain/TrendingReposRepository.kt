package com.mohnage7.domain

import com.mohnage7.domain.model.TrendingRepo
import io.reactivex.rxjava3.core.Single


interface TrendingReposRepository {
    fun getTrendingReposCacheFirst(): Single<List<TrendingRepo>>
    fun getTrendingReposRemoteFirst(): Single<List<TrendingRepo>>
}