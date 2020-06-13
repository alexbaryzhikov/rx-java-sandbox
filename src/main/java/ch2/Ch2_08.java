package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_08 {

    public static void main(String[] args) {
        Observable.just(1, 2, 3)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done")
                )
                .dispose();
    }
}
