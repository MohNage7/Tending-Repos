package com.mohnage7.data

import com.mohnage7.local.LocalDataSource
import com.mohnage7.local.db.entity.TrendingReposEntity
import com.mohnage7.remote.RemoteDataSource
import com.mohnage7.remote.model.TrendingRepoResponse
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TrendingReposRepositoryImplTest {

    private lateinit var SUT: TrendingReposRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Rule
    @JvmField
    var testSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        SUT = TrendingReposRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Test
    fun getTrendingReposRemote_whenCalled_shouldComplete() {
        //Prepare
        mockRemoteSuccess()
        mockCacheSuccess()

        //Action
        val result = SUT.getTrendingReposRemoteFirst().test()

        //Assertion
        result.assertComplete()
    }

    @Test
    fun getTrendingReposRemote_whenCalled_remoteDataSource_shouldInvoked() {
        //Prepare
        mockRemoteSuccess()
        mockCacheSuccess()

        //Action
        SUT.getTrendingReposRemoteFirst().test()

        //Assertion
        verify(localDataSource).getTrendingRepos()
        verify(remoteDataSource).getTrendingRepos()
        verify(localDataSource).insertAll(listOf())
    }


    @Test
    fun getTrendingReposRemote_whenCalled_localDataSource_shouldInvoked() {
        //Prepare
        mockRemoteSuccess()
        mockCacheSuccess()

        //Action
        SUT.getTrendingReposRemoteFirst().test()

        //Assertion
        verify(localDataSource).getTrendingRepos()
        verify(localDataSource).insertAll(listOf())
    }


    @Test
    fun getTrendingReposCache_whenCalled_localDataSource_shouldInvoked() {
        //Prepare
        mockCacheSuccess()
        mockRemoteSuccess()

        //Action
        SUT.getTrendingReposCacheFirst().test()

        //Assertion
        verify(localDataSource).getTrendingRepos()
        verify(localDataSource).insertAll(listOf())
    }


    @Test
    fun getTrendingReposCache_whenCalled_remoteDataSource_shouldInvoked() {
        //Prepare
        mockCacheSuccess()
        mockRemoteSuccess()

        //Action
        SUT.getTrendingReposCacheFirst().test()

        //Assertion
        verify(remoteDataSource).getTrendingRepos()
    }


    @Test
    fun getTrendingReposCache_whenCalled_shouldComplete() {
        //Prepare
        mockCacheSuccess()
        mockRemoteSuccess()

        //Action
        val test = SUT.getTrendingReposCacheFirst().test()

        //Assertion
        test.assertComplete()
    }

    @Test
    fun getTrendingReposCache_whenCalled_insertAll_shouldNotInvoked() {
        //Prepare
        mockCacheSuccess(listOf(TrendingReposEntity(1, "", "", "", "", "", 1)))
        mockRemoteSuccess()

        //Action
        SUT.getTrendingReposCacheFirst().test()

        //Assertion
        verify(localDataSource).getTrendingRepos()
        verify(localDataSource, never()).insertAll(anyList())
    }


    private fun mockRemoteSuccess() {
        `when`(remoteDataSource.getTrendingRepos())
            .thenReturn(
                Single.just(mock(TrendingRepoResponse::class.java))
            )
    }

    private fun mockCacheSuccess(reposList: List<TrendingReposEntity> = listOf()) {
        `when`(localDataSource.getTrendingRepos())
            .thenReturn(
                Single.just(reposList)
            )
        doNothing().`when`(localDataSource).insertAll(listOf())
    }
}