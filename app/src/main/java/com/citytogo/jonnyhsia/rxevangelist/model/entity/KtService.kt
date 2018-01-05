package com.citytogo.jonnyhsia.rxevangelist.model.entity

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.internal.operators.observable.ObservableFromArray

/**
 * Created by JonnyHsia on 17/10/24.
 */
class KtService {

    fun rx() {
        val onNext = Consumer<Int> { println(it) }
        val onError = Consumer<Throwable> { println(it.message) }

        Observable.create<Int> {
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
        }.subscribe(onNext, onError)


    }

    fun <T> rxFromArray(array: Array<T>) {
        ObservableFromArray<T>(array)
                .subscribe({

                }, {

                })
    }


}