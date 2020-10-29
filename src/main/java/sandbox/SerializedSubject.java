package sandbox;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.Arrays;

import static util.Utils.*;

public class SerializedSubject {

    public static void main(String[] args) {
        Subject<String> subj = PublishSubject.<String>create().toSerialized();
        Disposable d = subj.subscribe(it -> log("onNext", it));

        Arrays.asList(
                createWorker(subj, "worker1"),
                createWorker(subj, "worker2"),
                createWorker(subj, "worker3")
        ).forEach(Thread::start);

        sleep(5000);
        d.dispose();
    }

    @NonNull
    private static Thread createWorker(Subject<String> subj, String name) {
        return new Thread(() -> {
            Thread.currentThread().setName(name);
            for (int i = 0; i < 10; i++) {
                sleep(randomLong(100, 500));
                subj.onNext(name + " - " + i);
            }
        });
    }
}
