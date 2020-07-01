package ch7;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class BoundaryWindow {
    public static void main(String[] args) {
        Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(it -> (it + 1) * 300)
                .window(cutOffs)
                .flatMapSingle(obs -> obs.reduce("", (acc, x) -> acc + (acc.equals("") ? "" : "|") + x))
                .subscribe(it -> log("RECV", it));
        sleep(5000);
    }
}
