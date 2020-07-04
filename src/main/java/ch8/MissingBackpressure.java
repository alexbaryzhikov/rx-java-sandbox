package ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import util.Utils;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class MissingBackpressure {
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(Utils::intenseCalculation)
                .subscribe(
                        it -> log("onNext", it),
                        Throwable::printStackTrace
                );
        sleep(Long.MAX_VALUE);
    }
}
