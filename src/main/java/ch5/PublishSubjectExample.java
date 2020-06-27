package ch5;

import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class PublishSubjectExample {
    public static void main(String[] args) {
        Subject<String> subject = PublishSubject.create();
        subject.map(String::length)
                .subscribe(System.out::println);

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();
    }
}
