package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class MultipleSubscribeOn {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .filter(s -> s.length() == 5)
                .subscribeOn(Schedulers.io())
                .subscribe(i -> System.out.println("Received " + i +
                        " on thread " + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
    }
}
