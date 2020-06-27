package ch5;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class RefCount {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> src = Observable.interval(1, TimeUnit.SECONDS)
                .publish()
                .refCount();
        // publish().refCount() is equivalent to share()

        src.take(5)
                .subscribe(x -> System.out.println("O1: " + x));
        TimeUnit.SECONDS.sleep(2);
        src.take(3)
                .subscribe(x -> System.out.println("O2: " + x));
        TimeUnit.SECONDS.sleep(3);

        // Starts over.
        src.subscribe(x -> System.out.println("O3: " + x));
        TimeUnit.SECONDS.sleep(3);
    }
}
