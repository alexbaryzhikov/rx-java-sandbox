package ch10;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.Test;

import static util.Utils.log;

public class DebuggingRx {

    @Test
    public void debug_walkthrough() {
        TestObserver<String> testObserver = new TestObserver<>();
        Observable<String> items = Observable.just(
                "521934/2342/Foxtrot",
                "Bravo/12112/78886/Tango",
                "283242/4542/Whiskey/2348562");

        items
                .doOnNext(it -> log("items ->", it))
                .concatMap(s -> Observable.fromArray(s.split("/")))
                .doOnNext(it -> log("concatMap ->", it))
                .filter(it -> it.matches("[A-Za-z]+"))
                .doOnNext(it -> log("filter ->", it))
                .subscribe(testObserver);

        System.out.println(testObserver.values());
        testObserver.assertValues("Foxtrot", "Bravo", "Tango", "Whiskey");
    }
}
