package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

public class Ch3_18 {

    public static void main(String[] args) {
        Observable.just(3,1,5,6,1,3,1)
                .sorted(Comparator.reverseOrder())
                .subscribe(System.out::print)
                .dispose();
    }
}
