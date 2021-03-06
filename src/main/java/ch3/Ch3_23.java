package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_23 {

    public static void main(String[] args) {
        Observable.just(1, 1, 2, 3, 5, 8)
                .reduce(0, Integer::sum)
                .subscribe(System.out::println)
                .dispose();
    }
}
