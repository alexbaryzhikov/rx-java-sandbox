package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_03 {

    public static void main(String[] args) {
        Observable.just("Alpha", "Beta")
                .filter(s -> s.startsWith("Z"))
                .defaultIfEmpty("None")
                .subscribe(System.out::println)
                .dispose();
    }
}
