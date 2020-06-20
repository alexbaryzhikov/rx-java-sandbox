package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_17 {

    public static void main(String[] args) {
        Observable.just(3,1,5,6,1,3,1)
                .sorted()
                .subscribe(System.out::print)
                .dispose();
    }
}
