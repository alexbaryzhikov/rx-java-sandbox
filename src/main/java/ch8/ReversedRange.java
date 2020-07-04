package ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.atomic.AtomicInteger;

import static util.Utils.log;
import static util.Utils.sleep;

public class ReversedRange {
    public static void main(String[] args) {
        rangeReversed(1, 100)
                .subscribeOn(Schedulers.computation())
                .doOnNext(it -> log("push", it))
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    sleep(10);
                    log("onNext", it);
                });

        sleep(5000);
    }

    private static Flowable<Integer> rangeReversed(int initial, int count) {
        return Flowable.generate(
                () -> new AtomicInteger(initial + 1),
                (state, emitter) -> {
                    int value = state.decrementAndGet();
                    if (value > initial - count) {
                        emitter.onNext(value);
                    }
                    if (value - 1 <= initial - count) {
                        emitter.onComplete();
                    }
                }
        );
    }
}
