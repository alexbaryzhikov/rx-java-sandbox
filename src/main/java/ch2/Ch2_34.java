package ch2;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.ResourceObserver;

import java.util.concurrent.TimeUnit;

public class Ch2_34 {

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        ResourceObserver<Long> myObserver = new ResourceObserver<Long>() {
            @Override
            public void onNext(@NonNull Long value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };
        Disposable disposable = source.subscribeWith(myObserver);
        TimeUnit.SECONDS.sleep(5);
        disposable.dispose();
    }
}
