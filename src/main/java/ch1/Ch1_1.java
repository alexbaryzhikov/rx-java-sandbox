package ch1;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Ch1_1 {

    public static void main(String[] args) {
        Disposable d = Observable.just("Foo", "Bar", "Baz")
                .map(s -> s + "_")
                .subscribe(System.out::println);
        System.out.println("Done");
        d.dispose();
    }
}
