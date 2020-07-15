package sandbox;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static util.Utils.log;

public class RetryWithDelay2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> onFinished = new CompletableFuture<>();
        Disposable d = Single.fromCallable(() -> ThreadLocalRandom.current().nextInt(10))
                .map(it -> {
                    if (it != 7) throw new IllegalArgumentException(it.toString());
                    return it;
                })
                .retryWhen(errors -> errors.flatMap(it -> {
                    log("onError", it);
                    return Flowable.timer(1, TimeUnit.SECONDS);
                }))
                .subscribe(it -> {
                    log("onSuccess", it);
                    onFinished.complete(null);
                });
        onFinished.get();
        d.dispose();
    }
}
