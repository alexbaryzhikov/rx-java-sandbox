package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Ch2_35 {
    private static final CompositeDisposable disposables = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable disposable2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        disposables.addAll(disposable1, disposable2);
        TimeUnit.SECONDS.sleep(5);
        disposables.dispose();
        TimeUnit.SECONDS.sleep(5);
    }
}
