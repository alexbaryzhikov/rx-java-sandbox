package sandbox;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.Utils.*;

public class SquashDuplicates {

    public static void main(String[] args) {
        Queue<Task> queue = new LinkedBlockingQueue<>();
        startConsumer(queue);
        startProducer(queue);
        sleep(5000);
    }

    private static void startProducer(Queue<Task> queue) {
        Observable.interval(50, TimeUnit.MILLISECONDS)
                .take(20)
                .subscribe(it -> {
                    log("PRODUCER", "Create " + it);
                    queue.add(new Task("Foo", it.intValue(), randomLong(100, 200)));
                });
    }

    private static void startConsumer(Queue<Task> queue) {
        Observable.fromIterable(queue)
                .subscribeOn(Schedulers.computation())
                .subscribe(it -> {
                    log("CONSUMER", "Start " + it);
                    sleep(it.duration);
                    log("CONSUMER", "Finish " + it);
                });
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
        public String toString() {
            return "Task{" +
                    "key='" + key + '\'' +
                    ", id=" + id +
                    ", duration=" + duration +
                    '}';
        }
    }
}
