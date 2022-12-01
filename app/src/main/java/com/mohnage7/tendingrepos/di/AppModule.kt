package com.mohnage7.tendingrepos.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindSchedulerProvider(
        schedulerProviderImpl: SchedulerProviderImpl
    ): SchedulerProvider
}
