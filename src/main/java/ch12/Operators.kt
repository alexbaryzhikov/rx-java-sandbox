package ch12

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import util.Utils.log

fun main() {
    Observable.just("Alpha", "Beta", "Gama", "Delta", "Epsilon")
            .toSet()
            .blockingSubscribe { log("onNext", it) }
}

fun <T> Observable<T>.toSet(): Single<Set<T>> =
        collect({ HashSet<T>() }) { acc, next -> acc.add(next) }
                .map { it as Set<T> }