package ch3;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Ch3_62 {
    public static void main(String[] args) throws InterruptedException {
        Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
                .doAfterTerminate(() -> System.out.println("doAfterTerminate"))
                .doFinally(() -> System.out.println("doFinally"))
                .doOnDispose(() -> System.out.println("doOnDispose"))
                .doOnSubscribe(d -> System.out.println("doOnSubscribe"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
        TimeUnit.SECONDS.sleep(3);
        disp.dispose();
        TimeUnit.SECONDS.sleep(1);
    }
}
