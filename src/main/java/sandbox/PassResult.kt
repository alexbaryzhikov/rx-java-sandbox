package sandbox

import io.reactivex.rxjava3.core.Observable
import util.Utils.log
import util.Utils.sleep
import java.util.concurrent.TimeUnit

fun main() {
    Observable.interval(0, 1, TimeUnit.SECONDS)
            .map { runCatching { it } }
            .subscribe { log("onNext", it.getOrNull()) }
    sleep(5000)
}