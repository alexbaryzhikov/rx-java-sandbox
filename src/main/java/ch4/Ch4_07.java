package ch4;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Ch4_07 {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(2, 0, 3, 10, 7)
                .flatMap(i -> i == 0 ?
                        Observable.empty() :
                        Observable.interval(i, TimeUnit.SECONDS)
                                .map(i2 -> i + "s interval: " + (i2 + 1) * i + " seconds elapsed"))
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(12);
    }
}
