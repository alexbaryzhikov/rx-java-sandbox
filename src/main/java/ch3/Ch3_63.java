package ch3;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Ch3_63 {
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("mm:ss");
        System.out.println(LocalDateTime.now().format(f));
        Disposable d = Observable.just("Alpha", "Beta", "Gamma")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now().format(f) + " Received: " + s));
        TimeUnit.SECONDS.sleep(3);
        d.dispose();
    }
}
