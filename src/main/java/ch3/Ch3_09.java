package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_09 {

    public static void main(String[] args) {
        Observable.just(1, 2, 2, 5, 6, 5, 1)
                .distinct()
                .subscribe(System.out::println)
                .dispose();
    }
}
