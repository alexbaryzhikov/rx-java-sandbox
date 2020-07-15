package sandbox;

import io.reactivex.rxjava3.core.Single;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class DelayEmission {
    public static void main(String[] args) {
        Single.just(0)
                .doOnSubscribe(it -> log("onSubscribe", ""))
                .delay(3, TimeUnit.SECONDS)
                .subscribe(it -> log("onNext", it));
        sleep(5000);
    }
}
