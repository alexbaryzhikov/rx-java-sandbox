package ch4;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ch4_13 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Observable<String> src1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Integer> src2 = Observable.range(1, 6);
        Observable<Long> timer = Observable.interval(1, TimeUnit.SECONDS);

        CompletableFuture<Void> future = new CompletableFuture<>();
        Observable.zip(src1, src2, timer, (s, i, l) -> l + ": " + s + "-" + i)
                .doFinally(() -> future.complete(null))
                .subscribe(System.out::println);
        future.get();
    }
}
