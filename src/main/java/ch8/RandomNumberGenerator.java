package ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

import static util.Utils.log;
import static util.Utils.sleep;

public class RandomNumberGenerator {
    public static void main(String[] args) {
        Flowable<Integer> rng = Flowable.generate(
                emitter -> emitter.onNext(ThreadLocalRandom.current().nextInt(1000))
        );
        rng.subscribeOn(Schedulers.computation())
                .doOnNext(it -> log("push", it))
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    sleep(50);
                    log("onNext", it);
                });
        sleep(5000);
    }
}
