package ch3;

import com.google.common.collect.ImmutableList;
import io.reactivex.rxjava3.core.Observable;

public class Ch3_40 {

    public static void main(String[] args) {
        Observable.just(1, 5, 5, 1, 2, 4, 3, 3)
                .collect(ImmutableList.Builder<Integer>::new, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(System.out::println)
                .dispose();
    }
}
