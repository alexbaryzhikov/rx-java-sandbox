package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.util.HashSet;

public class Ch3_39 {

    public static void main(String[] args) {
        Observable.just(1, 5, 5, 1, 2, 4, 3, 3)
                .collect(HashSet<Integer>::new, HashSet::add)
                .subscribe(System.out::println)
                .dispose();
    }
}
