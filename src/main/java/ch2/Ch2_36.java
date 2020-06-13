package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_36 {

    public static void main(String[] args) {
        Observable.create(observableEmitter -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (observableEmitter.isDisposed()) return;
                    observableEmitter.onNext(i);
                }
                observableEmitter.onComplete();
            } catch (Throwable e) {
                observableEmitter.onError(e);
            }
        })
                .take(10)
                .subscribe(System.out::println)
                .dispose();
    }
}
