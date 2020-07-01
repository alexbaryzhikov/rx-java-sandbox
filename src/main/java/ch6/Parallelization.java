package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Parallelization {
    public static void main(String[] args) throws InterruptedException {
        Observable.range(1, 10)
                .flatMap(x -> Observable.just(x)
                        .observeOn(Schedulers.computation())
                        .map(Parallelization::intenseCalculation))
                .subscribe(i -> System.out.println("Received " + i + " " + LocalTime.now() +
                        " on " + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(5);
    }

    public static <T> T intenseCalculation(T value) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }
}
