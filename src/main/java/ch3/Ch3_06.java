package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_06 {

    public static void main(String[] args) {
        Observable.range(0, 100)
                .take(3)
                .subscribe(System.out::println)
                .dispose();
    }
}
