package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class Ch2_14 {

    public static void main(String[] args) {
        ConnectableObservable<String> src = Observable.just("Alpha", "Beta", "Gamma").publish();
        Disposable d1 = src.subscribe(s -> System.out.println("Observer 1: " + s));
        Disposable d2 = src.map(String::length)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        src.connect();

        d1.dispose();
        d2.dispose();
    }
}
