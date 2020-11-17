package sandbox;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.subjects.CompletableSubject;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;

public class AwaitWithTimeout {

    public static void main(String[] args) {
        CompletableSubject subj = CompletableSubject.create();
        Disposable d = Observable.timer(2, TimeUnit.SECONDS)
                .doOnNext(it -> log("Timer", "onNext"))
                .subscribe(it -> subj.onComplete());
        log("Main", subj.blockingAwait(3, TimeUnit.SECONDS));
        d.dispose();
    }
}
