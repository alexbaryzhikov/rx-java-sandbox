package sandbox;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.Utils.*;

@SuppressWarnings("UnusedReturnValue")
public class SquashDuplicates {

    public static void main(String[] args) {
        LinkedBlockingQueue<Task> queue = new LinkedBlockingQueue<>();
        startConsumer(queue);
        startProducer(queue);
        sleep(5000);
    }

    private static Disposable startConsumer(LinkedBlockingQueue<Task> queue) {
        return Observable.<Task>generate(emitter -> emitter.onNext(queue.take()))
                .subscribeOn(Schedulers.computation())
                .subscribe(it -> {
                    log("CONSUMER", "Start  " + it);
                    sleep(it.duration);
                    log("CONSUMER", "Finish " + it);
                });
    }

    private static Disposable startProducer(Queue<Task> queue) {
        List<String> keys = Arrays.asList("foo", "bar", "baz");
        return Observable.interval(50, TimeUnit.MILLISECONDS)
                .take(20)
                .subscribe(it -> {
                    String key = keys.get(randomInt(0, keys.size()));
                    Task task = new Task(key, it.intValue(), randomLong(100, 200));
                    log("PRODUCER", "Create " + task);
                    squash(queue, task);
                });
    }

    private static void squash(Queue<Task> queue, Task task) {
        queue.remove(task);
        queue.add(task);
        log("SQUASH", queue.toString());
    }

    private static class Task {
        String key;
        int id;
        long duration;

        public Task(String key, int id, long duration) {
            this.key = key;
            this.id = id;
            this.duration = duration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return key.equals(task.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return key + ':' + id + ':' + duration;
        }
    }
}
