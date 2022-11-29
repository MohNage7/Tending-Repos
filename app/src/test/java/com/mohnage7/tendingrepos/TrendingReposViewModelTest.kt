package com.mohnage7.tendingrepos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohnage7.domain.GetTrendingReposUseCase
import com.mohnage7.domain.model.TrendingRepo
import com.mohnage7.tendingrepos.ui.ViewState
import com.mohnage7.tendingrepos.utils.SchedulerProviderTestImpl
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class TrendingReposViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var SUT: TrendingReposViewModel

    @Mock
    lateinit var getTendingReposUseCase: GetTrendingReposUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        SUT = TrendingReposViewModel(
            getTendingReposUseCase, SchedulerProviderTestImpl()
        )
    }

    @Test
    fun fetchTrendingRepos_whenCalled_thenReturnLoadingState() {
        //Prepare
        val repo = trendingRepo()
        delayerPublishSubject(repo)

        //Action
        SUT.trendingRepoViewState.observeForever {}
        SUT.fetchTrendingRepos()

        //Assertion
        assertEquals(
            SUT.trendingRepoViewState.value, ViewState.Loading
        )
    }

    @Test
    fun fetchTrendingRepos_whenCalled_thenReturnCorrectData() {
        //Prepare
        val repo = trendingRepo()
        Mockito.`when`(getTendingReposUseCase())
            .thenReturn(
                Single.just(listOf(repo))
            )

        //Action
        SUT.fetchTrendingRepos()

        //Assertion
        val actualName =
            (SUT.trendingRepoViewState.value as ViewState.Success<List<TrendingRepo>>).data[0].name

        assertEquals(actualName, repo.name)
    }


    @Test
    fun fetchTrendingRepos_whenCalled_thenGetTendingReposUseCase_shouldInvoked() {
        //Prepare
        val repo = trendingRepo()
        Mockito.`when`(getTendingReposUseCase())
            .thenReturn(
                Single.just(listOf(repo))
            )

        //Action
        SUT.fetchTrendingRepos()

        //Assertion
        verify(getTendingReposUseCase).invoke()
    }


    @Test
    fun getAll_onError_state() {
        //Prepare
        val repo = trendingRepo()
        val delayer: PublishSubject<List<TrendingRepo>> = delayerPublishSubject(repo)

        //Action
        SUT.trendingRepoViewState.observeForever {}
        SUT.fetchTrendingRepos()

        //Assert
        assertEquals(
            SUT.trendingRepoViewState.value, ViewState.Loading
        )
        delayer.onError(Exception("Fail"))
        assertEquals(
            SUT.trendingRepoViewState.value, ViewState.Failure("Fail")
        )
    }

    private fun delayerPublishSubject(repo: TrendingRepo): PublishSubject<List<TrendingRepo>> {
        val delayer: PublishSubject<List<TrendingRepo>> = PublishSubject.create()
        Mockito.`when`(getTendingReposUseCase())
            .thenReturn(
                Single.just(listOf(repo)).delaySubscription(delayer)
            )
        return delayer
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