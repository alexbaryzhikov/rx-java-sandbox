package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_08 {

    public static void main(String[] args) {
        Observable.range(0, 100)
                .skip(90)
                .subscribe(System.out::println)
                .dispose();
    }
}
