package ch4;

import io.reactivex.rxjava3.core.Observable;

public class Ch4_19 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .groupBy(String::length)
                .flatMapSingle(grp -> grp
                        .reduce("", (x, y) -> x.equals("") ? y : x + ", " + y)
                        .map(s -> grp.getKey() + ": " + s))
                .subscribe(System.out::println);
    }
}
