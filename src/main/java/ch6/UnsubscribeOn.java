package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class UnsubscribeOn {
    public static void main(String[] args) {
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> log("DISPOSE-1", ""))
                .unsubscribeOn(Schedulers.io())
                .doOnDispose(() -> log("DISPOSE-2", ""))
                .subscribe(i -> log("RECV", i));
        sleep(3500);
        d.dispose();
        sleep(1000);
    }
}
