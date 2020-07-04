package ch8;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static util.Utils.log;
import static util.Utils.sleep;

public class ObservableToFlowable {
    public static void main(String[] args) {
        Observable.range(1, 1000)
                .toFlowable(BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
                .subscribe(it -> log("onNext", it));
        sleep(1000);
    }
}
