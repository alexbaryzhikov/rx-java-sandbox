package ch1;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Ch1_3 {

    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .blockingSubscribe(System.out::println);
        System.out.println("Done");
    }
}
