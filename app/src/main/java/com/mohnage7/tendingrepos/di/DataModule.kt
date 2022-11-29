package com.mohnage7.tendingrepos.di

import com.mohnage7.domain.OfflineFirstTrendingRepository
import com.mohnage7.domain.TrendingReposRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsTopicRepository(
        offlineFirstTrendingRepository: OfflineFirstTrendingRepository
    ): TrendingReposRepository
}
