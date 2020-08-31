package sandbox;

import io.reactivex.rxjava3.core.Single;

import static util.Utils.log;

public class RecoverFromError {
    public static void main(String[] args) {
        Integer result = Single
                .<Integer>fromCallable(() -> {
                    throw new IllegalStateException("One");
                })
                .doOnError(it -> log("SOURCE", "First failure: " + it))
                .onErrorResumeNext(throwable -> Single.fromCallable(() -> {
                    throw new IllegalStateException("Two");
                }))
                .doOnError(it -> log("SOURCE", "Second failure: " + it))
                .blockingGet();

        log("MAIN", "result " + result);
    }
}
