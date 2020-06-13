package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_27 {

    public static void main(String[] args) {
        Observable.fromCallable(() -> 1 / 0)
                .subscribe(
                        i -> System.out.println("RECEIVE " + i),
                        e -> System.err.println("ERROR " + e)
                )
                .dispose();
    }
}
