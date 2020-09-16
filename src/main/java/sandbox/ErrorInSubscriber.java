package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class ErrorInSubscriber {
    public static void main(String[] args) {
        Disposable d = Observable.just(1, 2 ,3)
                .subscribeOn(Schedulers.computation())
                .subscribe(
                        it -> {
                            if (it == 2) throw new RuntimeException("BOOM!");
                            log("SUBSCRIBER", it);
                        },
                        th -> log("SUBSCRIBER", "Error: " + th)
                );

        sleep(TimeUnit.SECONDS.toMillis(2));
        d.dispose();
    }
}
