package ch9;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;

import static util.Utils.log;

public class JoinToStringTransformer {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(joinToString("/"))
                .subscribe(it -> log("onNext", it));
    }

    static <T> ObservableTransformer<T, String> joinToString(String separator) {
        return upstream -> upstream
                .collect(StringBuilder::new, (sb, item) -> {
                    if (sb.length() == 0)
                        sb.append(item);
                    else
                        sb.append(separator).append(item);
                })
                .map(StringBuilder::toString)
                .toObservable();
    }
}
