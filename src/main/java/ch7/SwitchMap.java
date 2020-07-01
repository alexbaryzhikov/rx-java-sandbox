package ch7;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static util.Utils.log;

public class SwitchMap {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> onComplete = new CompletableFuture<>();
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta",
                "Epsilon", "Zeta", "Eta", "Theta", "Iota")
                .concatMap(it -> Observable.just(it)
                        .delay(randomSleepTime(), TimeUnit.MILLISECONDS, Schedulers.single()));
        Observable.interval(0, 4, TimeUnit.SECONDS)
                .doOnNext(it -> log("TRIGGER", it))
                .switchMap(it -> source
                        .doOnDispose(() -> log("DISPOSE", "Start next"))
                        .observeOn(Schedulers.computation()))
                .doOnComplete(() -> onComplete.complete(null))
                .subscribe(it -> log("RECV", it));
        onComplete.get();
    }

    public static int randomSleepTime() {
        return ThreadLocalRandom.current().nextInt(2000);
    }
}
