package ch2;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Ch2_17 {

    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .blockingSubscribe(s ->
                        System.out.println(LocalDateTime.now().getSecond() + " " + s)
                );
    }
}
