package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceScheduler {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Scheduler scheduler = Schedulers.from(executor);
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(scheduler)
                .doFinally(executor::shutdown)
                .subscribe(System.out::println);
    }
}
