package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_59 {

    public static void main(String[] args) {
        Observable.just(5, 3, 7)
                .reduce(Integer::sum)
                .doOnSuccess(i -> System.out.println("Emitting: " + i))
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
