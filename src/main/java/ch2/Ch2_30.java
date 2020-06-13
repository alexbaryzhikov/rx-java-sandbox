package ch2;

import io.reactivex.rxjava3.core.Maybe;

public class Ch2_30 {

    public static void main(String[] args) {
        Maybe.just(100)
                .subscribe(
                        s -> System.out.println("RECEIVE " + s),
                        e -> System.err.println("ERROR " + e),
                        () -> System.out.println("Done")
                )
                .dispose();
        Maybe.empty()
                .subscribe(
                        s -> System.out.println("RECEIVE " + s),
                        e -> System.err.println("ERROR " + e),
                        () -> System.out.println("Done")
                )
                .dispose();
    }
}
