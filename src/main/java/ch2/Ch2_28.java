package ch2;

import io.reactivex.rxjava3.core.Single;

public class Ch2_28 {

    public static void main(String[] args) {
        Single.just("Hello")
                .map(String::length)
                .subscribe(
                        s -> System.out.println("RECEIVE " + s),
                        e -> System.err.println("ERROR " + e)
                )
                .dispose();
    }
}
