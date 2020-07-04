package ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class OnBackPressureDrop {
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(it -> log("onDrop", it))
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    sleep(5);
                    log("onNext", it);
                });
        sleep(5000);
    }
}
