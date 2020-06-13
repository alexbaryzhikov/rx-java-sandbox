package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Ch2_19 {

    public static void main(String[] args) throws InterruptedException {
        ConnectableObservable<Long> src = Observable.interval(1, TimeUnit.SECONDS).publish();
        src.subscribe(x -> System.out.println("Observer 1: " + x));
        src.connect();
        TimeUnit.SECONDS.sleep(3);
        src.take(3).blockingSubscribe(x -> System.out.println("Observer 2: " + x));
    }
}
