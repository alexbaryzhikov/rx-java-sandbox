package ch2;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;

public class Ch2_06 {

    public static void main(String[] args) {
        Observable.fromIterable(Arrays.asList(1,2,3,4,5))
                .map(x -> x * x)
                .filter(x -> x > 5)
                .subscribe(System.out::println)
                .dispose();
    }
}
