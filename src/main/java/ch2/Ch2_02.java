package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import static util.Utils.log;

public class Ch2_02 {

    public static void main(String[] args) {
        Observable<String> publisher = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                log("SOURCE", "Complete");
                emitter.onComplete();
            } catch (Throwable th) {
                log("SOURCE", "Failed with " + th);
                emitter.onError(th);
            }
        });

        Disposable d = publisher.subscribe(
                it -> {
                    if (it.length() < 5) throw new IllegalArgumentException();
                    log("OBSERVER", "onNext " + it);
                },
                th -> log("OBSERVER", "onError " + th)
        );

        d.dispose();
    }
}
