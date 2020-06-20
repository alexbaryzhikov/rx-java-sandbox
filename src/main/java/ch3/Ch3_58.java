package ch3;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Ch3_58 {

    public static void main(String[] args) throws InterruptedException {
        Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
        TimeUnit.SECONDS.sleep(3);
        disp.dispose();
        TimeUnit.SECONDS.sleep(1);
    }
}
