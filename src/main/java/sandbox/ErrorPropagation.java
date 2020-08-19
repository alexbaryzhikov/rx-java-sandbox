package sandbox;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

import static util.Utils.log;

public class ErrorPropagation {
    public static void main(String[] args) {
        Single<String> source = Single.just("OK");

        Disposable d = source
                .map(it -> {
                    throw new RuntimeException();
                })
                .subscribe(
                        it -> log("OBSERVER", "onSuccess " + it),
                        th -> log("OBSERVER", "onError " + th)
                );

        d.dispose();
    }
}
