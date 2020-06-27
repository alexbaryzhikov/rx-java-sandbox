package ch5;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.ThreadLocalRandom;

public class AutoConnect {

    public static void main(String[] args) {
        Observable<Integer> src = Observable.range(1, 3)
                .map(i -> randomInt())
                .publish()
                .autoConnect(2);

        src.subscribe(i -> System.out.println("O1: " + i));
        src.reduce(0, Integer::sum)
                .subscribe(i -> System.out.println("O2: " + i));
        src.subscribe(i -> System.out.println("O3: " + i));
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(10);
    }
}
