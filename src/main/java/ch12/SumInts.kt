package ch12

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import util.Utils.log


fun main() {
    Observable.just(100, 50, 200, 150)
            .sum()
            .blockingSubscribe { log("onNext", it) }
}

fun Observable<Int>.sum(): Single<Int> = reduce(0) { acc, next -> acc + next }