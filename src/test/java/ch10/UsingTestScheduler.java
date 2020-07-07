package ch10;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class UsingTestScheduler {

    @Test
    public void fast_forward_time() {
        TestScheduler testScheduler = new TestScheduler();
        TestObserver<Long> testObserver = new TestObserver<>();

        Observable<Long> minuteTicker =
                Observable.interval(1, TimeUnit.MINUTES, testScheduler);
        minuteTicker.subscribe(testObserver);

        //Fast forward by 30 seconds
        testScheduler.advanceTimeBy(30, TimeUnit.SECONDS);
        testObserver.assertValueCount(0);

        //Fast forward to 70 seconds after subscription
        testScheduler.advanceTimeTo(70, TimeUnit.SECONDS);
        testObserver.assertValueCount(1);

        //Fast Forward to 90 minutes after subscription
        testScheduler.advanceTimeTo(90, TimeUnit.MINUTES);
        testObserver.assertValueCount(90);
    }

    @Test
    public void override_computation_scheduler() {
        TestScheduler testScheduler = new TestScheduler();
        RxJavaPlugins.setComputationSchedulerHandler(it -> testScheduler);
        TestObserver<Long> testObserver =
                Observable.interval(1, TimeUnit.MINUTES).test();

        testScheduler.advanceTimeTo(90, TimeUnit.MINUTES);
        testObserver.assertValueCount(90);
    }
}
