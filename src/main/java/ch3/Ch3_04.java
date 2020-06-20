package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_04 {

    public static void main(String[] args) {
        Observable.just(1,2,3)
                .filter(i -> i > 5)
                .switchIfEmpty(Observable.just(5, 6, 7))
                .subscribe(System.out::println)
                .dispose();
    }
}
