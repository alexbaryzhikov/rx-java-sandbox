package sandbox;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static util.Utils.*;

public class TaskManager {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.enable();
        generateTasks(taskManager);
        sleep(TimeUnit.SECONDS.toMillis(2));
        taskManager.disable();
    }

    private static void generateTasks(TaskManager taskManager) {
        Consumer<String> success = s -> {
            log("ACTION", "Process " + s);
            sleep(randomSleepTime(10, 300));
        };
        Consumer<String> failure = s -> {
            log("ACTION", "Process " + s);
            sleep(randomSleepTime(10, 300));
            throw new RuntimeException();
        };
        ResultHandler<String> handler = new ResultHandler<String>() {
            @Override
            public void onSuccess(String value) {
                log("RESULT", "onSuccess " + value);
            }

            @Override
            public void onFailure(String value, Throwable error) {
                log("RESULT", "onFailure " + value + ": " + error);
            }
        };

        for (int i = 0; i < 18; i++) {
            String groupId;
            String value;
            switch (i % 3) {
                case 0:
                    groupId = "A";
                    value = "A" + i;
                    break;
                case 1:
                    groupId = "B";
                    value = "B" + i;
                    break;
                default:
                    groupId = "C" + i; // Singleton group for each element.
                    value = "C" + i;
                    break;
            }
            Consumer<String> action = ThreadLocalRandom.current().nextDouble(1.0) < 0.8 ? success : failure;
            taskManager.submit(groupId, value, action, handler);
        }
    }

    private final Subject<Task> subj = PublishSubject.create();
    private Disposable disposable;

    public void enable() {
        disposable = subj
                .doOnNext(it -> log("SUBMIT", it.value))
                .groupBy(task -> task.groupId)
                .flatMap(group -> group
                        .observeOn(Schedulers.io())
                        .map(task -> {
                            try {
                                task.action.accept(task.value);
                            } catch (Throwable th) {
                                return new Result(task, th);
                            }
                            return new Result(task, null);
                        }))
                .subscribe(result -> {
                    if (result.error == null) {
                        result.task.handler.onSuccess(result.task.value);
                    } else {
                        result.task.handler.onFailure(result.task.value, result.error);
                    }
                });

    }

    public void disable() {
        if (disposable != null) disposable.dispose();
        disposable = null;
    }

    public <T> void submit(String groupId, T value, Consumer<T> action, ResultHandler<T> handler) {
        subj.onNext(Task.create(groupId, value, action, handler));
    }

    public interface ResultHandler<T> {

        void onSuccess(T value);

        void onFailure(T value, Throwable error);
    }

    private static class Task {
        String groupId;
        Object value;
        Consumer<Object> action;
        ResultHandler<Object> handler;

        private Task() {
        }

        @SuppressWarnings("unchecked")
        static <T> Task create(String groupId, T value, Consumer<T> action, ResultHandler<T> handler) {
            Task task = new Task();
            task.groupId = groupId;
            task.value = value;
            task.action = (Consumer<Object>) action;
            task.handler = (ResultHandler<Object>) handler;
            return task;
        }
    }

    private static class Result {
        Task task;
        Throwable error;

        public Result(Task task, Throwable error) {
            this.task = task;
            this.error = error;
        }
    }
}
