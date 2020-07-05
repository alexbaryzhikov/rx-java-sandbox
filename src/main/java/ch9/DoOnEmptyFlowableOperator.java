package ch9;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableOperator;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.subscribers.DisposableSubscriber;

import static util.Utils.log;

public class DoOnEmptyFlowableOperator {

    public static void main(String[] args) {
        Flowable.range(1, 5)
                .lift(doOnEmpty(() -> log("onEmpty-1", "")))
                .subscribe(it -> log("onNext-1", it));

        Flowable.<Integer>empty()
                .lift(doOnEmpty(() -> log("onEmpty-2", "")))
                .subscribe(it -> log("onNext-2", it));
    }

    static <T> FlowableOperator<T, T> doOnEmpty(Action action) {
        return subscriber -> new DisposableSubscriber<T>() {
            private boolean isEmpty = true;

            @Override
            public void onNext(T t) {
                isEmpty = false;
                subscriber.onNext(t);
            }

            @Override
            public void onError(Throwable t) {
                subscriber.onError(t);
            }

            @Override
            public void onComplete() {
                if (isEmpty) {
                    try {
                        action.run();
                    } catch (Throwable th) {
                        subscriber.onError(th);
                        return;
                    }
                }
                subscriber.onComplete();
            }
        };
    }
}
