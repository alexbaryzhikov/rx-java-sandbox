package ch2;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Ch2_18 {

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> src = Observable.interval(1, TimeUnit.SECONDS);
        src.subscribe(x -> System.out.println("Observer 1: " + x));
        TimeUnit.SECONDS.sleep(3);
        src.take(3).blockingSubscribe(x -> System.out.println("Observer 2: " + x));
    }
}
