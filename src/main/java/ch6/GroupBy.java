package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import util.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import static util.Utils.log;

public class GroupBy {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int coreCount = Runtime.getRuntime().availableProcessors();
        CompletableFuture<Void> onComplete = new CompletableFuture<>();

        Observable.range(1, 100)
                .doOnNext(x -> log("EMIT", x))
                .groupBy(x -> ThreadLocalRandom.current().nextInt(coreCount))
                .doOnNext(x -> log("GROUP", "----- "+x.getKey()))
                .flatMap(grp -> grp.observeOn(Schedulers.io())
                        .map(Utils::intenseCalculation))
                .doOnComplete(() -> onComplete.complete(null))
                .subscribe(i -> log("RECV", i));

        onComplete.get();
    }
}
