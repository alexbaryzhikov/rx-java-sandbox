package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class Ch2_01 {

    public static void main(String[] args) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("Foo");
            emitter.onNext("Bar");
            emitter.onNext("Baz");
            emitter.onComplete();
        })
                .subscribe(System.out::println)
                .dispose();
    }
}
