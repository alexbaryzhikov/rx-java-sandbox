package ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import static util.Utils.*;

public class SubscriberExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> onComplete = new CompletableFuture<>();
        Flowable.range(1, 1000)
                .doOnNext(it -> log("PUSH", it))
                .observeOn(Schedulers.io())
                .map(it -> intenseCalculation(it, 200))
                .subscribe(
                        new Subscriber<Integer>() {
                            Subscription subscription;
                            AtomicInteger count = new AtomicInteger(0);

                            @Override
                            public void onSubscribe(Subscription subscription) {
                                this.subscription = subscription;
                                log("onSubscribe", "Request 40 items");
                                subscription.request(40);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                sleep(50);
                                log("onNext", integer);
                                if (count.incrementAndGet() % 20 == 0 && count.get() >= 40) {
                                    log("onNext", "Request 20 more");
                                    subscription.request(20);
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                t.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                log("onComplete", "");
                                onComplete.complete(null);
                            }
                        }
                );
        onComplete.get();
    }
}
