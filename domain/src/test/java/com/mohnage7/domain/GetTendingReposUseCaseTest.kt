package com.mohnage7.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohnage7.domain.model.TrendingRepo
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetTendingReposUseCaseTest {


    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var SUT: GetTrendingReposUseCase

    @Mock
    private lateinit var trendingReposRepository: TrendingReposRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        SUT = GetTrendingReposUseCase(trendingReposRepository)
    }

    @Test
    fun fetchTrendingRepos_whenCalled_thenTrendingReposRepository_shouldInvoked() {
        //Prepare
        Mockito.`when`(trendingReposRepository.getTrendingRepos()).thenReturn(Single.just(listOf()))

        //Action
        SUT.invoke()

        //Assertion
        verify(trendingReposRepository).getTrendingRepos()
    }


    @Test
    fun getTrendingRepos_whenCalled_thenReturnCorrectData() {
        //Prepare
        val expectedValue = listOf(trendingRepo())
        Mockito.`when`(trendingReposRepository.getTrendingRepos())
            .thenReturn(Single.just(expectedValue))

        //Action
        val result = SUT.invoke()

        //Assertion
        result.test().assertValue(expectedValue)
    }


    private fun trendingRepo() = TrendingRepo(
        image = "",
        author = "Nageh",
        name = "Trending Repo",
        description = "Trending Github repositories https://github.com/MohNage7/Tending-Repos",
        stars = 100,
        language = "Kotlin"
    )
}