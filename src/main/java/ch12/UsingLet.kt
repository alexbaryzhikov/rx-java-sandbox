package ch12

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.subscribeBy
import util.Utils.log

fun main() {
    val source = Observable.just(180.0, 160.0, 140.0, 100.0, 120.0)

    source.publish()
            .autoConnect(2)
            .let { Singles.zip(it.sum(), it.count()) { sum, count -> sum / count } }
            .subscribeBy { log("average", it) }

    source
            .reduce(Pair(0.0, 0)) { acc, next -> Pair(acc.first + next, acc.second + 1) }
            .map { (sum, count) -> sum / count }
            .subscribeBy { log("average", it) }
}

fun Observable<Double>.sum(): Single<Double> = reduce(0.0) { acc, next -> acc + next }