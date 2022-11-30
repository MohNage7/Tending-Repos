package com.mohnage7.tendingrepos.di

import com.mohnage7.data.TrendingReposRepositoryImpl
import com.mohnage7.domain.TrendingReposRepository
import com.mohnage7.local.LocalDataSource
import com.mohnage7.local.db.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsTopicRepository(
        offlineFirstTrendingRepository: TrendingReposRepositoryImpl
    ): TrendingReposRepository


    @Binds
    fun bindsLocalDataSource(
        localDataSource: LocalDataSourceImpl
    ): LocalDataSource
}
