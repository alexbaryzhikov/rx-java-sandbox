package ch12

import io.reactivex.rxjava3.core.Observable
import util.Utils.log
import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun main() {
    println("${LocalDate.now().daysTo(LocalDate.of(2020, 9, 1))} days")

    Observable.just("Alpha", "Beta", "Gama", "Delta", "Epsilon")
            .map { it.length }
            .scan(0) { acc, next -> acc + next }
            .subscribe { log("onNext", it) }
}

fun LocalDate.daysTo(other: LocalDate): Long =
        ChronoUnit.DAYS.between(this, other)

