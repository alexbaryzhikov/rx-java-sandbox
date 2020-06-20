package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_13 {

    public static void main(String[] args) {
        Observable.just(1, 2, 4, 5, 6)
                .elementAt(3)
                .subscribe(System.out::println)
                .dispose();
    }
}
