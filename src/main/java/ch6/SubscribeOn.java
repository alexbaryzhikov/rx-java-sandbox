package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SubscribeOn {

    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> src = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(SubscribeOn::intenseCalculation)
                .map(String::length);
        src.subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));
        src.subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(10);
    }

    private static <T> T intenseCalculation(T value) {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException ignored) {
        }
        return value;
    }
}
