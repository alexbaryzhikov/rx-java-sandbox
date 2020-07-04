package ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static util.Utils.log;
import static util.Utils.sleep;

public class FlowableToObservable {
    public static void main(String[] args) {
        Flowable<Integer> ints = Flowable.range(1, 1000)
                .subscribeOn(Schedulers.computation());

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .flatMap(it -> ints.map(i -> i + "-" + it).toObservable())
                .subscribe(it -> log("onNext", it));

        sleep(1000);
    }
}
