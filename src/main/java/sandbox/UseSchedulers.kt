package sandbox

import io.reactivex.rxjava3.schedulers.Schedulers
import util.Utils.log
import java.util.concurrent.TimeUnit

fun main() {
    with (Schedulers.computation()) {
        scheduleDirect { log("direct", "") }
        scheduleDirect({ log("delayed", "") }, 1, TimeUnit.SECONDS)
        schedulePeriodicallyDirect({ log("periodic", "") }, 0, 1, TimeUnit.SECONDS)
    }
    TimeUnit.SECONDS.sleep(5)
}