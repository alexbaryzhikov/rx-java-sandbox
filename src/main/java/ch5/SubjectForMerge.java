package ch5;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.concurrent.TimeUnit;

public class SubjectForMerge {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(x -> (x + 1) + " seconds");
        Observable<String> src2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(x -> (x + 1) * 300 + " millis");
        Subject<String> subject = PublishSubject.create();
        subject.subscribe(System.out::println);
        src1.subscribe(subject);
        src2.subscribe(subject);
        TimeUnit.SECONDS.sleep(3);
    }
}
