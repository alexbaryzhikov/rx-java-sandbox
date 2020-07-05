package ch9;

import com.google.common.collect.ImmutableList;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;

import static util.Utils.log;

public class ToImmutableListFlowableTransformer {

    public static void main(String[] args) {
        Flowable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(toImmutableList())
                .subscribe(it -> log("onNext", it));

        Flowable.range(1, 10)
                .compose(toImmutableList())
                .subscribe(it -> log("onNext", it));
    }

    static <T> FlowableTransformer<T, ImmutableList<T>> toImmutableList() {
        return upstream -> upstream
                .collect(ImmutableList::<T>builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .toFlowable();
    }
}
