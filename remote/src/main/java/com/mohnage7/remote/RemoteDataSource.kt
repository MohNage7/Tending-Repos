package com.mohnage7.remote

import com.mohnage7.remote.model.TrendingRepoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("search/repositories")
    fun getTrendingRepos(@Query("q") query: String = "language=+sort:stars"): Single<TrendingRepoResponse>
}