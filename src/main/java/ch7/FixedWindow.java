package ch7;

import io.reactivex.rxjava3.core.Observable;

import static util.Utils.log;

public class FixedWindow {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .window(3, 1)
                .flatMapSingle(obs -> obs.reduce("", (total, next) ->
                        total + (total.equals("") ? "" : "|") + next))
                .subscribe(it -> log("RECV", it));
    }
}
