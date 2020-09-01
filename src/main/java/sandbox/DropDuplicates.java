package sandbox;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static util.Utils.*;

public class DropDuplicates {

    public static void main(String[] args) {
        Disposable d = getSource()
                .observeOn(Schedulers.io())
                .compose(dropDuplicates())
                .doOnNext(task -> {
                    log("PROCESSOR", "Process " + task);
                    sleep(task.duration);
                })
                .subscribe(it -> log("SUBSCRIBER", "<<-- " + it));
        sleep(TimeUnit.SECONDS.toMillis(5));
        d.dispose();
    }

    @NonNull
    private static Observable<Task> getSource() {
        return Observable.create(emitter -> {
            for (int i = 0; i < 20; i++) {
                String id = Arrays.asList("Alpha", "Beta", "Gamma").get(randomInt(0, 3));
                Task task = new Task(id, i, randomLong(10, 200));
                log("SOURCE", "-->> " + task);
                emitter.onNext(task);
                sleep(randomLong(10, 100));
            }
            emitter.onComplete();
        });
    }

    @NonNull
    private static ObservableTransformer<Task, Task> dropDuplicates() {
        final Map<String, Long> lastExecutionTimes = new HashMap<>();
        return source -> source
                .filter(task -> lastExecutionTimes.getOrDefault(task.id, 0L) < task.timestamp)
                .doOnNext(task -> lastExecutionTimes.put(task.id, System.currentTimeMillis()));
    }

    private static class Task {
        String id;
        int index;
        long duration;
        long timestamp;

        public Task(String id, int index, long duration) {
            this.id = id;
            this.index = index;
            this.duration = duration;
            this.timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return id + "-" + index;
        }
    }
}
