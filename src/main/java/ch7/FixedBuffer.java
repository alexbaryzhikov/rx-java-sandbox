package ch7;

import io.reactivex.rxjava3.core.Observable;

import static util.Utils.log;

public class FixedBuffer {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .buffer(3, 1)
                .filter(it -> it.size() == 3)
                .subscribe(x -> log("RECV", x));
    }
}
