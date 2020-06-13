package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_12 {

    public static void main(String[] args) {
        Observable<String> src = Observable.just("Alpha", "Beta", "Gamma");
        src.subscribe(s -> System.out.println("Observer 1: " + s)).dispose();
        src.map(String::length)
                .filter(x -> x >= 5)
                .subscribe(s -> System.out.println("Observer 2: " + s))
                .dispose();
    }
}
