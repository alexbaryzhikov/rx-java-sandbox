package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_10 {

    public static void main(String[] args) {
        Observable.just("One", "Two", "Three", "Four", "Five")
                .distinct(String::length)
                .subscribe(System.out::println)
                .dispose();
    }
}
