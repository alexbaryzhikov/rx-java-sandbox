package sandbox;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static util.Utils.log;
import static util.Utils.sleep;

public class Multicast {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> onComplete = new CompletableFuture<>();

        Flowable<Integer> src = Flowable.range(1, 1000)
                .subscribeOn(Schedulers.newThread())
                .doOnNext(it -> log("EMIT", it))
                .doOnComplete(() -> onComplete.complete(null))
                .publish()
                .autoConnect(2);

        Thread t1 = new Thread(() -> src
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    log("RECV1", it);
                    sleep(5);
                }));
        Thread t2 = new Thread(() -> src
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    log("RECV2", it);
                    sleep(10);
                }));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        onComplete.get();
    }
}
