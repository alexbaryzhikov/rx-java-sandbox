package ch8;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static util.Utils.log;

public class CustomFlowable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Flowable<Integer> source = Flowable.create(emitter -> {
            for (int i = 0; i < 1000; i++) {
                if (emitter.isCancelled()) return;
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);

        CompletableFuture<Void> onComplete = new CompletableFuture<>();
        source.observeOn(Schedulers.io())
                .subscribe(
                        it -> log("onNext", it),
                        Throwable::printStackTrace,
                        () -> onComplete.complete(null)
                );
        onComplete.get();
    }
}
