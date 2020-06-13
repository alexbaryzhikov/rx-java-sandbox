package ch2;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Ch2_21 {

    public static void main(String[] args) throws InterruptedException {
        Observable.never()
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(3);
    }
}
