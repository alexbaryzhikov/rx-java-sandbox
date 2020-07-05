package ch9;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleTransformer;

import java.util.Collection;
import java.util.Collections;

import static util.Utils.log;

public class ToUnmodifiableCollectionSingleTransformer {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList()
                .compose(toUnmodifiable())
                .subscribe(it -> log("onNext", it));
    }

    private static <T> SingleTransformer<Collection<T>, Collection<T>> toUnmodifiable() {
        return singleObserver -> singleObserver.map(Collections::unmodifiableCollection);
    }
}
