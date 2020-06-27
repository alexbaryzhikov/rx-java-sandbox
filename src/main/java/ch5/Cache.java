package ch5;

import io.reactivex.rxjava3.core.Observable;

public class Cache {
    public static void main(String[] args) {
        Observable<Integer> src = Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .scan(0, Integer::sum)
                .cache();

        src.subscribe(System.out::println);
    }
}
