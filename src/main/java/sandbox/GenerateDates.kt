package sandbox

import io.reactivex.rxjava3.core.Emitter
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.functions.BiConsumer
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.kotlin.subscribeBy
import util.Utils.log
import java.time.LocalDate
import java.util.concurrent.atomic.AtomicReference

fun main() {
    val source = Flowable.generate(
            Supplier { AtomicReference(LocalDate.now()) },
            BiConsumer { state: AtomicReference<LocalDate>, emitter: Emitter<LocalDate> ->
                val nextDate = state.getAndUpdate { it.plusDays(1) }
                emitter.onNext(nextDate)
            })

    source.take(10).subscribeBy { log("onNext", it) }
}