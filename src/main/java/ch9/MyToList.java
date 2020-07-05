package ch9;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOperator;
import io.reactivex.rxjava3.observers.DisposableObserver;

import java.util.ArrayList;
import java.util.List;

import static util.Utils.log;

public class MyToList {

    public static void main(String[] args) {
        Observable.range(1, 10)
                .lift(myToList())
                .subscribe(it -> log("onNext", it));

        Observable.<Integer>empty()
                .lift(myToList())
                .subscribe(it -> log("onNext", it));
    }

    static <T> ObservableOperator<List<T>, T> myToList() {
        return observer -> new DisposableObserver<T>() {
            private final List<T> list = new ArrayList<>();

            @Override
            public void onNext(@NonNull T t) {
                list.add(t);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                observer.onNext(list);
                observer.onComplete();
            }
        };
    }
}
