package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ConcurrencyIntro {
    public static void main(String[] args) {
        Observable<String> src1 = Observable.just("Alpha", "Beta", "Gamma")
                .subscribeOn(Schedulers.computation())
                .map(ConcurrencyIntro::intenseCalculation);
        Observable<Integer> src2 = Observable.range(1, 3)
                .subscribeOn(Schedulers.computation())
                .map(ConcurrencyIntro::intenseCalculation);
        Observable.zip(src1, src2, (s, i) -> s + " " + i)
                .blockingSubscribe(System.out::println);
    }

    private static <T> T intenseCalculation(T value) {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException ignored) {
        }
        return value;
    }
}
