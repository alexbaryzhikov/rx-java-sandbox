package ch9;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOperator;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.observers.DisposableObserver;

import static util.Utils.log;

public class DoOnEmptyOperator {

    public static void main(String[] args) {
        Observable.range(1, 5)
                .lift(doOnEmpty(() -> log("onEmpty-1", "")))
                .subscribe(it -> log("onNext-1", it));

        Observable.<Integer>empty()
                .lift(doOnEmpty(() -> log("onEmpty-2", "")))
                .subscribe(it -> log("onNext-2", it));
    }

    private static <T> ObservableOperator<T, T> doOnEmpty(Action action) {
        return observer -> new DisposableObserver<T>() {
            boolean isEmpty = true;

            @Override
            public void onNext(@NonNull T value) {
                isEmpty = false;
                observer.onNext(value);
            }

            @Override
            public void onError(@NonNull Throwable t) {
                observer.onError(t);
            }

            @Override
            public void onComplete() {
                if (isEmpty) {
                    try {
                        action.run();
                    } catch (Throwable th) {
                        onError(th);
                        return;
                    }
                }
                observer.onComplete();
            }
        };
    }
}
