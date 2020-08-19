package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class GetSingleItem {
    public static void main(String[] args) {
        ConnectableObservable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .doOnNext(it -> log("SOURCE", "onNext " + it))
                .publish();
        Disposable sourceDisposable = source.connect();

        sleep(TimeUnit.SECONDS.toMillis(2));

        Disposable observerDisposable = source.firstOrError()
                .subscribe(it -> log("OBSERVER", "onNext " + it));

        sleep(TimeUnit.SECONDS.toMillis(2));

        observerDisposable.dispose();
        sourceDisposable.dispose();
    }
}
