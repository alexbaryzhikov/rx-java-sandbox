package ch4;

import io.reactivex.rxjava3.core.Observable;

public class Ch4_18 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .groupBy(String::length)
                .flatMapSingle(Observable::toList)
                .subscribe(System.out::println);
    }
}
