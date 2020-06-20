package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_15 {

    public static void main(String[] args) {
        Observable.just("Foo", "Bar", "Baz")
                .startWithItem("Zoom")
                .subscribe(System.out::println)
                .dispose();
    }
}
