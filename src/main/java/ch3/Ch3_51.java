package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_51 {

    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .doAfterNext(s -> System.out.println("After 1: " + s))
                .doAfterNext(s -> System.out.println("After 2: " + s))
                .map(String::length)
                .doAfterNext(s -> System.out.println("After 3: " + s))
                .subscribe(i -> System.out.println("Received: " + i))
                .dispose();
    }
}
