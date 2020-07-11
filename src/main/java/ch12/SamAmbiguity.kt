package ch12

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.subscribeBy
import util.Utils.log

fun main() {
    val strings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
    val ints = Observable.range(1, 4)

    Observables.zip(strings, ints) { a, b -> "$a $b" }
            .subscribe { log("zip", it) }

    strings.count()
            .subscribeBy { log("single", it) }
}