package sandbox;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Processor {
    public static void main(String[] args) throws InterruptedException {
        FlowableProcessor<String> processor = PublishProcessor.<String>create().toSerialized();

        List<String> result = new ArrayList<>();
        Disposable d = processor
                .observeOn(Schedulers.newThread())
                .subscribe(s -> {
                    System.out.println("[" + Thread.currentThread().getName() + "] " + s);
                    result.add(s);
                });

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int n = i;
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    processor.onNext("T" + n + " " + j);
                }
            });
            threads.add(t);
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        TimeUnit.SECONDS.sleep(1);
        d.dispose();

        System.out.println("\nTotal: " + result.size());
        result.sort(String::compareTo);
        String last = "";
        for (String s : result) {
            if (!last.equals(s.split(" ")[0])) {
                System.out.println();
                System.out.print(s.split(" ")[0] + ": ");
                last = s.split(" ")[0];
            }
            System.out.print(s.split(" ")[1] + " ");
        }
        System.out.println();
    }
}
