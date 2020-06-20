package sandbox;

import io.reactivex.rxjava3.core.Observable;

public class Fibs {

    static Observable<Integer> fibs() {
        return Observable.concat(
                Observable.just(0, 1),
                Observable.defer(() ->
                        fibs().buffer(2, 1)
                                .flatMap(it ->
                                        Observable.fromIterable(it)
                                                .reduce(Integer::sum)
                                                .toObservable())));
    }

    public static void main(String[] args) {
        fibs().take(10)
                .subscribe(System.out::println)
                .dispose();
    }
}
