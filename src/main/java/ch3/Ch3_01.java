package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_01 {

    public static void main(String[] args) {
        Observable.range(1, 100)
                .takeWhile(i -> i < 5)
                .subscribe(System.out::println)
                .dispose();
    }
}
