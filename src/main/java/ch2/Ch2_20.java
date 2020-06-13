package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_20 {

    public static void main(String[] args) {
        Observable.empty()
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done")
                )
                .dispose();
    }
}
