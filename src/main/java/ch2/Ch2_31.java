package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_31 {

    public static void main(String[] args) {
        Observable.just("Alpha", "Beta")
                .firstElement()
                .subscribe(
                        s -> System.out.println("RECEIVE " + s),
                        e -> System.err.println("ERROR " + e),
                        () -> System.out.println("Done")
                )
                .dispose();
    }
}
