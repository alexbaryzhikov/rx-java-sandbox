package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import static util.Utils.log;

public class DelayError {
    public static void main(String[] args) {
        Observable.range(1, 100)
                .flatMapSingle(
                        it -> it % 10 == 0 ?
                                Single.error(new RuntimeException("error-" + it)) :
                                Single.just(it),
                        true)
                .subscribe(
                        it -> log("onNext", it),
                        Throwable::printStackTrace);
    }
}
