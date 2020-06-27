package ch5;

import io.reactivex.rxjava3.core.Observable;

public class Replay2 {
    public static void main(String[] args) {
        Observable<String> src = Observable.just("Alpha", "Beta", "Gamma")
                .replay(1)
                .autoConnect();

        src.subscribe(x -> System.out.println("O1: " + x));
        src.subscribe(x -> System.out.println("O2: " + x));

        System.out.println();

        Observable<String> src2 = Observable.just("Alpha", "Beta", "Gamma")
                .replay(1)
                .refCount();

        src2.subscribe(x -> System.out.println("O1: " + x));
        src2.subscribe(x -> System.out.println("O2: " + x));
    }
}
