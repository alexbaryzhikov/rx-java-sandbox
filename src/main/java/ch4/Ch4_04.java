package ch4;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Ch4_04 {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> l + 1)
                .map(l -> "Source1: " + l + " seconds");
        Observable<String> src2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source2: " + l + " milliseconds");
        Observable.merge(src1, src2)
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(3);
    }
}
