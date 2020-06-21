package sandbox;

import io.reactivex.rxjava3.core.Observable;

public class Fibs {

    static Observable<Integer> fibs() {
        return Observable.just(0, 1)
                .concatWith(Observable.defer(() -> fibs()
                        .buffer(2, 1)
                        .flatMapMaybe(it -> Observable.fromIterable(it)
                                .reduce(Integer::sum))));
    }

    public static void main(String[] args) {
        fibs().take(10)
                .subscribe(System.out::println)
                .dispose();
    }
}
