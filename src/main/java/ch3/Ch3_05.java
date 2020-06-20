package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_05 {

    public static void main(String[] args) {
        Observable.range(0, 10)
                .filter(it -> it % 2 == 0)
                .subscribe(System.out::println)
                .dispose();
    }
}
