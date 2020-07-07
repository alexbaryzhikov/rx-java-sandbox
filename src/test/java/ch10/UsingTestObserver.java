package ch10;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsingTestObserver {

    @Test
    public void different_assertions() {
        Observable<Long> source = Observable.interval(100, TimeUnit.MILLISECONDS).take(3);

        TestObserver<Long> testObserver = new TestObserver<>();

        assertFalse(testObserver.hasSubscription());

        source.subscribe(testObserver);

        assertTrue(testObserver.hasSubscription());

        testObserver.awaitDone(1, TimeUnit.SECONDS);

        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(3)
                .assertValues(0L, 1L, 2L);
    }
}
