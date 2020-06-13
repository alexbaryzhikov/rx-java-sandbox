package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_22 {

    public static void main(String[] args) {
        Observable.error(() -> new IllegalStateException("Fault"))
                .blockingSubscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done")
                );
    }
}
