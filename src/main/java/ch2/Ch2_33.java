package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Ch2_33 {

    public static void main(String[] args) throws InterruptedException {
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);
        d.dispose();
        TimeUnit.SECONDS.sleep(3);
    }
}
