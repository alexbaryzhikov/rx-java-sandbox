package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

public class Ch3_19 {

    public static void main(String[] args) {
        Observable.just("Turret", "Slug", "Tor", "Ra")
                .sorted(Comparator.comparingInt(String::length))
                .subscribe(System.out::println)
                .dispose();
    }
}
