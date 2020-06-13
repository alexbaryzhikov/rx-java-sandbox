package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_11 {

    public static void main(String[] args) {
        Observable<String> src = Observable.just("Foo", "Bar", "Baz");
        src.subscribe(s -> System.out.println("Observer 1: " + s)).dispose();
        src.subscribe(s -> System.out.println("Observer 2: " + s)).dispose();
    }
}
