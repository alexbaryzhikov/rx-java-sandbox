package sandbox;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class FailFast {
    public static void main(String[] args) {
        Disposable disposable = Single.just(0)
                .flatMap(item -> Single.just("Alpha")
                        .doOnSuccess(it -> log("SOURCE", "doOnSuccess " + it))
                        .doOnError(it -> log("SOURCE", "doOnError " + it))
                )
                .flatMap(item -> Single.fromCallable(new Failure())
                        .doOnSuccess(it -> log("SOURCE", "doOnSuccess " + it))
                        .doOnError(it -> log("SOURCE", "doOnError " + it))
                )
                .subscribe(
                        it -> log("SOURCE", "onSuccess " + it),
                        it -> log("SOURCE", "onError " + it)
                );

        sleep(TimeUnit.SECONDS.toMillis(1));
        disposable.dispose();
    }

    private static class Failure implements Callable<Void> {

        @Override
        public Void call() {
            throw new RuntimeException("No can do!");
        }
    }
}
