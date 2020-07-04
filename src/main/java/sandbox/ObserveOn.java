package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static util.Utils.log;

public class ObserveOn {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> src = Observable.just(1, 2, 3)
                .doOnNext(x -> log("Emit", x))
                .observeOn(Schedulers.newThread());
        Thread t1 = new Thread(() -> src.subscribe(x -> log("Recv", x)));
        Thread t2 = new Thread(() -> src.subscribe(x -> log("Recv", x)));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
