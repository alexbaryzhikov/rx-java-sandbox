package sandbox;

import io.reactivex.rxjava3.core.Observable;

public class Fibs {

    private static Observable<Integer> fibs() {
        return Observable.just(0, 1)
                .concatWith(Observable.defer(() -> fibs()
                        .window(2, 1)
                        .flatMapSingle(it -> it.reduce(0, Integer::sum))));
    }

    private static Observable<Integer> fibs2() {
        return Observable.just(0)
                .repeat()
                .scan(new int[]{0, 1}, (a, i) -> new int[]{a[1], a[0] + a[1]})
                .map(a -> a[0]);
    }

    public static void main(String[] args) {
        fibs().take(10).blockingSubscribe(it -> System.out.print(it + " "));
        System.out.println();
        fibs2().take(10).blockingSubscribe(it -> System.out.print(it + " "));
    }
}
