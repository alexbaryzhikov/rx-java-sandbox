package ch9;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;

import static util.Utils.log;

public class ToIndexedValueTransformer {
    public static void main(String[] args) {
        Observable<IndexedValue<String>> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(toIndexedValue());
        source.subscribe(it -> log("onNext-1", it));
        source.subscribe(it -> log("onNext-2", it));
    }

    static <T> ObservableTransformer<T, IndexedValue<T>> toIndexedValue() {
        return upstream -> upstream
                .zipWith(Observable.range(0, Integer.MAX_VALUE), IndexedValue::new);
    }

    static final class IndexedValue<T> {
        int index;
        T value;

        public IndexedValue(T value, int index) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "IndexedValue{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
        }
    }
}
