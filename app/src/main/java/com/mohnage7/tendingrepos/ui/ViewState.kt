package com.mohnage7.tendingrepos.ui

import androidx.lifecycle.MutableLiveData

sealed class ViewState<out T> where T : Any? {

    object Loading : ViewState<Nothing>()

    data class Success<T>(val data: T) : ViewState<T>()

    data class Failure(val errorMessage: String? = null) : ViewState<Nothing>()
}

fun <T> MutableLiveData<ViewState<T>>.toLoading() {
    value = ViewState.Loading
}

fun <T> MutableLiveData<ViewState<T>>.toSuccess(result: T) {
    value = ViewState.Success(result)
}

fun <T> MutableLiveData<ViewState<T>>.toFailure(message: String? = null) {
    value = ViewState.Failure(message)
}
