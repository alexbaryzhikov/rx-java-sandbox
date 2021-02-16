package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static util.Utils.log;
import static util.Utils.sleep;

public class SubscribeOn {
    public static void main(String[] args) {
        Observable.just(0)
                .doOnSubscribe(it -> log("onSubscribe", "0"))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> log("onSubscribe", "1"))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> log("onSubscribe", "2"))
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(it -> log("onSubscribe", "3"))
                .subscribe();
        sleep(100);
    }
}
