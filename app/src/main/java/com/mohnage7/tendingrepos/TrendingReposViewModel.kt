package com.mohnage7.tendingrepos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohnage7.domain.GetTendingReposUseCase
import com.mohnage7.domain.model.TrendingRepo
import com.mohnage7.tendingrepos.di.SchedulerProvider
import com.mohnage7.tendingrepos.ui.ViewState
import com.mohnage7.tendingrepos.ui.toFailure
import com.mohnage7.tendingrepos.ui.toLoading
import com.mohnage7.tendingrepos.ui.toSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class TrendingReposViewModel @Inject constructor(
    private val getTrendingReposUseCase: GetTendingReposUseCase,
    private val schedulerProvider: SchedulerProvider
) :
    ViewModel() {

    private var disposable: Disposable? = null
    val trendingRepoViewState: MutableLiveData<ViewState<List<TrendingRepo>>> = MutableLiveData()


    fun fetchTrendingRepos() {
        disposable = getTrendingReposUseCase.invoke()
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe { trendingRepoViewState.toLoading() }
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { result ->
                    trendingRepoViewState.toSuccess(result)
                },
                { onError ->
                    onError.message?.let { trendingRepoViewState.toFailure(it) }
                })
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}