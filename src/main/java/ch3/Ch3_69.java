package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Ch3_69 {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(2, TimeUnit.SECONDS)
                .doOnNext(i -> System.out.println("Emitted: " + i))
                .take(3)
                .timeInterval(TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received: " + i));
        TimeUnit.SECONDS.sleep(7);
    }
}
