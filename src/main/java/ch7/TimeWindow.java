package ch7;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class TimeWindow {
    public static void main(String[] args) {
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(it -> (it + 1) * 300)
                .window(1, TimeUnit.SECONDS)
                .flatMapSingle(obs -> obs.reduce("", (total, next) ->
                        total + (total.equals("") ? "" : "|") + next))
                .subscribe(it -> log("RECV", it));
        sleep(5000);
    }
}
