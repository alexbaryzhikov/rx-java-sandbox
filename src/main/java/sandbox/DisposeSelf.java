package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static util.Utils.log;
import static util.Utils.sleep;

public class DisposeSelf {
    public static void main(String[] args) {
        AtomicReference<Disposable> disposable = new AtomicReference<>();
        Disposable d = Observable.interval(0, 1, TimeUnit.SECONDS)
                .takeWhile(it -> it < 4)
                .doFinally(() -> disposable.get().dispose())
                .subscribe(it -> log("onNext", it));
        disposable.set(d);
        sleep(5000);
        log("isDisposed", disposable.get().isDisposed());
    }
}
