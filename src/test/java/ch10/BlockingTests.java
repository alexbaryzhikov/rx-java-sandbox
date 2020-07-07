package ch10;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class BlockingTests {

    @Test
    public void blocking_subscribe() {
        Observable<Long> source = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5);

        AtomicInteger counter = new AtomicInteger();
        source.blockingSubscribe(it -> counter.incrementAndGet());

        assertEquals(5, counter.get());
    }

    @Test
    public void blocking_first() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta")
                .filter(it -> it.length() == 4);

        String result = source.blockingFirst();

        assertEquals("Beta", result);
    }

    @Test
    public void blocking_get() {
        Single<List<String>> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta")
                .filter(it -> it.length() == 4)
                .toList();

        List<String> result = source.blockingGet();

        assertEquals(Arrays.asList("Beta", "Zeta"), result);
    }

    @Test
    public void blocking_iterable() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta")
                .filter(it -> it.length() == 5);

        Iterable<String> result = source.blockingIterable();

        result.forEach(it -> assertEquals(5, it.length()));
    }

    @Test
    public void blocking_for_each() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta")
                .filter(it -> it.length() == 5);

        source.blockingForEach(it -> assertEquals(5, it.length()));
    }

    @Test
    public void blocking_next() {
        Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS)
                .take(100);

        for (Long i : source.blockingNext()) {
            System.out.println(i);
        }
    }

    @Test
    public void blocking_latest() {
        Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS)
                .take(100);

        for (Long i : source.blockingLatest()) {
            System.out.println(i);
        }
    }

    @Test
    public void blocking_most_recent() {
        Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
                .take(5);

        for (Long i : source.blockingMostRecent(-1L)) {
            System.out.println(i);
        }
    }
}