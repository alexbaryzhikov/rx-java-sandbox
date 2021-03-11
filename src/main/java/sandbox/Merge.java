package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import util.Utils;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;

public class Merge {
    public static void main(String[] args) {
        Observable.merge(
                Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.io())
                        .take(3)
                        .map(it -> "foo " + it)
                        .doOnComplete(() -> log("foo complete")),
                Observable.interval(70, TimeUnit.MILLISECONDS, Schedulers.io())
                        .take(10)
                        .map(it -> "bar " + it)
                        .doOnComplete(() -> log("bar complete")),
                Observable.just(1, 2, 3)
                        .map(it -> "baz " + it)
                        .doOnComplete(() -> log("baz complete"))
        )
                .blockingSubscribe(Utils::log);
    }
}
