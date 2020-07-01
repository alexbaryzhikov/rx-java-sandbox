package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class ObserveOn {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
                .subscribeOn(Schedulers.io())
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .doOnNext(x -> System.out.println("Pass " + x + " on " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .reduce(Integer::sum)
                .doOnSuccess(x -> System.out.println("Compute " + x + " on " + Thread.currentThread().getName()))
                .observeOn(Schedulers.io())
                .map(Object::toString)
                .subscribe(x -> System.out.println("Receive " + x + " on " + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
    }
}
