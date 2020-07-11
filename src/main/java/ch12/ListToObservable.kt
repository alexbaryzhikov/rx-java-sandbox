package ch12

import io.reactivex.rxjava3.kotlin.toObservable
import util.Utils.log

fun main() {
    listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon").toObservable()
            .map { it.length }
            .subscribe { log("onNext", it) }
}