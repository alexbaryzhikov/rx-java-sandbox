package ch5;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Replay3 {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> src = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(x -> (x + 1) * 300)
                .replay(1, TimeUnit.SECONDS)
                .autoConnect();

        src.subscribe(x -> System.out.println("O1: " + x));
        TimeUnit.SECONDS.sleep(2);

        // Receives last second emissions.
        src.subscribe(x -> System.out.println("O2: " + x));
        TimeUnit.SECONDS.sleep(1);
    }
}
