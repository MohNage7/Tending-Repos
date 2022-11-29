package com.mohnage7.tendingrepos.utils

import com.mohnage7.tendingrepos.di.SchedulerProvider
import io.reactivex.rxjava3.schedulers.Schedulers


class SchedulerProviderTestImpl : SchedulerProvider {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}