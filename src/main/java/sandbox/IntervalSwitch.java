package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class IntervalSwitch {
    public static void main(String[] args) {
        Observable<Long> timer = Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.newThread());
        Subject<Integer> onSwitch = PublishSubject.create();
        Disposable d = onSwitch
                .startWithItem(0)
                .switchMap(it -> timer.doOnDispose(() -> log("DISPOSE", "")))
                .subscribe(it -> log("RECV", it));
        sleep(1500);
        onSwitch.onNext(0);
        sleep(5500);
        onSwitch.onNext(0);
        sleep(3500);
        d.dispose();
        sleep(2000);
    }
}
