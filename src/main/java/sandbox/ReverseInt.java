package sandbox;

import io.reactivex.rxjava3.core.Observable;

public class ReverseInt {

    private static int reverse(final int n) {
        Observable<Integer> digits = Observable.create(emitter -> {
            for (int i = n; i != 0; i /= 10) {
                emitter.onNext(i % 10);
            }
            emitter.onComplete();
        });
        return digits.reduce(0, (acc, i) -> acc * 10 + i).blockingGet();
    }

    public static void main(String[] args) {
        System.out.println(reverse(0));
        System.out.println(reverse(1234));
        System.out.println(reverse(12003));
    }
}
