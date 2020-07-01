package ch7;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class Sample {
    public static void main(String[] args) {
        Observable<String> source1 =
                Observable.interval(100, TimeUnit.MILLISECONDS)
                        .map(i -> (i + 1) * 100) //map to elapsed time
                        .map(i -> "SOURCE 1: " + i)
                        .take(10);
        Observable<String> source2 =
                Observable.interval(300, TimeUnit.MILLISECONDS)
                        .map(i -> (i + 1) * 300) //map to elapsed time
                        .map(i -> "SOURCE 2: " + i)
                        .take(3);
        Observable<String> source3 =
                Observable.interval(2000, TimeUnit.MILLISECONDS)
                        .map(i -> (i + 1) * 2000) //map to elapsed time
                        .map(i -> "SOURCE 3: " + i)
                        .take(2);
        Observable.concat(source1, source2, source3)
                .sample(500, TimeUnit.MILLISECONDS)
                .subscribe(it -> log("RECV", it));
        sleep(6000);
    }
}
