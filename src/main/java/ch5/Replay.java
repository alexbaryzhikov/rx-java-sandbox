package ch5;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Replay {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> src = Observable.interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect();

        src.subscribe(x -> System.out.println("O1: " + x));
        TimeUnit.SECONDS.sleep(3);
        src.subscribe(x -> System.out.println("O2: " + x));
        TimeUnit.SECONDS.sleep(3);
    }
}
