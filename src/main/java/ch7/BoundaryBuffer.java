package ch7;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class BoundaryBuffer {
    public static void main(String[] args) {
        Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(x -> (x + 1) * 300)
                .buffer(cutOffs)
                .subscribe(x -> log("RECV", x));
        sleep(5000);
    }
}
