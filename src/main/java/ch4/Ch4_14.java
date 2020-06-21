package ch4;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ch4_14 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Observable<String> strings = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        CompletableFuture<Void> done = new CompletableFuture<>();
        Observable.zip(strings, seconds, (s, l) -> s)
                .doFinally(() -> done.complete(null))
                .subscribe(s -> System.out.println(LocalTime.now() + " RECEIVED " + s));
        done.get();
    }
}
