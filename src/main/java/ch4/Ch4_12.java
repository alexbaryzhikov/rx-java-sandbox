package ch4;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Ch4_12 {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> src1 =
                Observable.interval(1, TimeUnit.SECONDS)
                        .take(2)
                        .map(l -> l + 1) // emit elapsed seconds
                        .map(l -> "Source1: " + l + " seconds");

        Observable<String> src2 =
                Observable.interval(300, TimeUnit.MILLISECONDS)
                        .map(l -> (l + 1) * 300) // emit elapsed millis
                        .map(l -> "Source2: " + l + " milliseconds");

        //emit Observable that emits first
        src1.ambWith(src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        TimeUnit.SECONDS.sleep(3);
    }
}
