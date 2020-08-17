package sandbox;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static util.Utils.*;

public class SwitchToNever {
    public static void main(String[] args) {
        Observable.interval(0, randomSleepTime(1000, 3000), TimeUnit.MILLISECONDS)
                .doOnNext(it -> log("source", it))
                .switchMap(it -> {
                    if (it % 3 != 0) {
                        return Observable.interval(0, 300, TimeUnit.MILLISECONDS);
                    } else {
                        return Observable.never();
                    }
                })
                .subscribe(it -> log("onNext", it));

        sleep(10000);
    }
}
