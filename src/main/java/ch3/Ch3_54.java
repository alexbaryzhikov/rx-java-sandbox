package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_54 {

    public static void main(String[] args) {
        Observable.just("One", "Two", "Three")
                .doOnEach(notification -> System.out.println("doOnEach: " + notification))
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
