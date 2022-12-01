package com.mohnage7.domain

import com.mohnage7.domain.model.TrendingRepo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetTrendingReposUseCase @Inject constructor(private val trendingReposRepository: TrendingReposRepository) {
    operator fun invoke(forceRemote: Boolean): Single<List<TrendingRepo>> {
        return if (forceRemote) trendingReposRepository.getTrendingReposRemoteFirst()
        else trendingReposRepository.getTrendingReposCacheFirst()
    }
}