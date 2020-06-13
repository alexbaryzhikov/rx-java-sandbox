package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class Ch2_03 {

    public static void main(String[] args) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onComplete();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        })
                .map(String::length)
                .filter(i -> i >= 5)
                .subscribe(System.out::println)
                .dispose();
    }
}
