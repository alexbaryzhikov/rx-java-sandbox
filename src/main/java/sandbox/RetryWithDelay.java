package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import util.Pair;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;

public class RetryWithDelay {

    public static void main(String[] args) {
        retryBlocking();
    }

    private static void retryBlocking() {
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(it -> log("onSubscribe", ""))
                .doOnNext(it -> log("onNext", it))
                .map(it -> {
                    throw new IllegalStateException("It's broken");
                })
                .retryWhen(errors -> onError(errors, 3, 2, TimeUnit.SECONDS))
                .blockingSubscribe(
                        it -> log("onNext", it),
                        it -> log("onError", it),
                        () -> log("onComplete", "")
                );
    }

    private static Observable<Long> onError(
            Observable<Throwable> errors,
            int retries,
            long interval,
            TimeUnit timeUnit
    ) {
        return errors
                .zipWith(Observable.range(1, retries), Pair::new)
                .flatMap(it -> {
                    log("onRetry", it.first);
                    if (it.second < retries) {
                        return Observable.timer(interval, timeUnit);
                    } else {
                        throw Exceptions.propagate(it.first);
                    }
                });
    }
}
