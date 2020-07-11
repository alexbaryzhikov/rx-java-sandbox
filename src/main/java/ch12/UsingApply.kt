package ch12

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import util.Utils.log
import java.util.concurrent.TimeUnit

fun main() {
    val statusObserver = PublishSubject.create<Long>()
            .apply { subscribe { log("status", it) } }

    Observable.interval(1, TimeUnit.SECONDS)
            .take(5)
            .publish()
            .autoConnect(2)
            .apply { subscribe(statusObserver) }
            .map { it * 100 }
            .subscribe { log("main", it) }

    TimeUnit.SECONDS.sleep(7)
}