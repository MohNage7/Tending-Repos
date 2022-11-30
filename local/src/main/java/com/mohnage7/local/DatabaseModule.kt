package com.mohnage7.local

import android.content.Context
import androidx.room.Room
import com.mohnage7.local.db.DATA_BASE_NAME
import com.mohnage7.local.db.TrendingRepoDatabase
import com.mohnage7.local.db.dao.TrendingRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideChannelDao(appDatabase: TrendingRepoDatabase): TrendingRepoDao {
        return appDatabase.trendingRepoDao
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TrendingRepoDatabase {
        return Room.databaseBuilder(
            appContext,
            TrendingRepoDatabase::class.java,
            DATA_BASE_NAME
        ).build()
    }
}