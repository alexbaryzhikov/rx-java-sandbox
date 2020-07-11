package sandbox

import io.reactivex.rxjava3.core.Observable
import util.Utils.log

fun main() {
    Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .materialize()
            .subscribe { log("onNext", it) }
}