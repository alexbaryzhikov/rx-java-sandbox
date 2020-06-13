package ch2;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_24 {
    private static int count = 3;

    public static void main(String[] args) {
        Observable<Integer> src = Observable.defer(() -> Observable.range(0, count));
        src.subscribe(s -> System.out.println("Observer 1: " + s)).dispose();
        count = 5;
        src.subscribe(s -> System.out.println("Observer 2: " + s)).dispose();
    }
}
