package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class Ch2_02 {

    public static void main(String[] args) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onComplete();
            } catch (Throwable th) {
                emitter.onError(th);
            }
        })
                .subscribe(
                        s -> {
                            if (s.length() < 5) throw new IllegalArgumentException();
                            System.out.println(s);
                        },
                        Throwable::printStackTrace
                )
                .dispose();
    }
}
