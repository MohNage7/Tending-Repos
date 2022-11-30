package com.mohnage7.data

import com.mohnage7.domain.model.TrendingRepo
import com.mohnage7.local.db.entity.TrendingReposEntity
import com.mohnage7.remote.model.TrendingRepoResponse
import io.reactivex.rxjava3.core.Single


fun Single<List<TrendingReposEntity>>.mapLocalToTrendingRepo(): Single<List<TrendingRepo>> =
    map { localList ->
        localList.map {
            TrendingRepo(
                id = it.id,
                image = it.image,
                name = it.name,
                author = it.author,
                description = it.description,
                language = it.language,
                stars = it.stars
            )
        }
    }


fun Single<TrendingRepoResponse>.mapResponseToTrendingRepo(): Single<List<TrendingRepo>> =
    map { it.mapResponseToTrendingRepo() }


fun TrendingRepoResponse.mapResponseToTrendingRepo(): List<TrendingRepo> = items.map {
    TrendingRepo(
        id = it.id,
        image = it.owner.avatarUrl,
        name = it.name,
        author = it.owner.name,
        description = it.description,
        language = it.language,
        stars = it.stars
    )
}


fun List<TrendingRepo>.mapToTrendingRepoEntity() = map {
    TrendingReposEntity(
        id = it.id,
        image = it.image,
        name = it.name,
        author = it.author,
        description = it.description,
        language = it.language,
        stars = it.stars
    )
}