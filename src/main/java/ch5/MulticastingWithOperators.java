package ch5;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.ThreadLocalRandom;

public class MulticastingWithOperators {
    public static void main(String[] args) {
        ConnectableObservable<Integer> ints = Observable.range(1, 3)
                .map(i -> randomInt())
                .publish();
        ints.subscribe(i -> System.out.println("Observer 1: " + i));
        ints.reduce(0, Integer::sum)
                .subscribe(i -> System.out.println("Observer 2: " + i));
        ints.connect();
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(10);
    }
}
