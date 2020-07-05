package ch9;

import com.google.common.collect.ImmutableList;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;

import static util.Utils.log;

public class ToImmutableListTransformer {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(toImmutableList())
                .subscribe(it -> log("onNext", it));

        Observable.range(1, 10)
                .compose(toImmutableList())
                .subscribe(it -> log("onNext", it));
    }

    static <T> ObservableTransformer<T, ImmutableList<T>> toImmutableList() {
        return upstream -> upstream
                .collect(ImmutableList::<T>builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .toObservable();
    }
}
