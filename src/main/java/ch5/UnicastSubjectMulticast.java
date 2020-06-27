package ch5;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;
import io.reactivex.rxjava3.subjects.UnicastSubject;

import java.util.concurrent.TimeUnit;

public class UnicastSubjectMulticast {
    public static void main(String[] args) throws InterruptedException {
        Subject<String> subject = UnicastSubject.create();
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> ((l + 1) * 300) + " milliseconds")
                .subscribe(subject);
        TimeUnit.SECONDS.sleep(2);
        //multicast to support multiple Observers
        Observable<String> multicast = subject.publish().autoConnect();
        //bring in first Observer
        multicast.subscribe(s -> System.out.println("Observer 1: " + s));
        TimeUnit.SECONDS.sleep(2);
        //bring in second Observer
        multicast.subscribe(s -> System.out.println("Observer 2: " + s));
        TimeUnit.SECONDS.sleep(1);
    }
}
