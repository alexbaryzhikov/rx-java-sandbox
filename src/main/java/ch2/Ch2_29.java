package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_29 {

    public static void main(String[] args) {
        Observable.just("Alpha", "Beta")
                .first("NULL")
                .subscribe(System.out::println)
                .dispose();
    }
}
