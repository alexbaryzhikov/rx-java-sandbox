package sandbox;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static util.Utils.*;

public class PersistentQueue {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Sender sender = new Sender();

        Single<Message> source = Single.create(emitter -> {
            if (repository.isEmpty()) {
                emitter.onError(new NoSuchElementException());
            } else {
                emitter.onSuccess(repository.head());
            }
        });

        Disposable disposable = source.subscribeOn(Schedulers.computation())
                .doOnSubscribe(it -> log("SOURCE", "doOnSubscribe"))
                .doOnSuccess(it -> log("SOURCE", "doOnSuccess " + it))
                .doOnError(it -> log("SOURCE", "doOnError " + it))
                .doOnSuccess(sender::send)
                .retryWhen(errors -> errors.flatMap(it -> {
                    log("SOURCE", "retryWhen " + it);
                    return Flowable.timer(1, TimeUnit.SECONDS);
                }))
                .repeat()
                .subscribe(it -> repository.popHead());

        generateMessages(repository);

        sleep(TimeUnit.SECONDS.toMillis(10));
        disposable.dispose();
    }

    /**
     * Generate messages with random intervals in between.
     */
    private static void generateMessages(Repository repository) {
        for (int i = 0; i < 10; i++) {
            sleep(randomSleepTime(0, 2000));
            repository.pushBack(new Message(i));
        }
    }

    static class Message {
        int id;

        public Message(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Message-" + id;
        }
    }

    static class Repository {
        private final Queue<Message> queue = new LinkedList<>();

        boolean isEmpty() {
            return queue.isEmpty();
        }

        synchronized void pushBack(Message message) {
            log("REPOSITORY", "Add " + message);
            queue.add(message);
        }

        Message head() {
            return queue.element();
        }

        synchronized void popHead() {
            log("REPOSITORY", "Remove " + head());
            queue.remove();
        }
    }

    static class Sender {
        private static final double SEND_SUCCESS_RATE = 0.6;

        /**
         * Sending takes random non-zero time. Sometimes sending fails.
         */
        void send(Message message) {
            log("SENDER", "Sending " + message);
            sleep(randomSleepTime(100, 1000));
            if (ThreadLocalRandom.current().nextDouble(1.0) < SEND_SUCCESS_RATE) {
                log("SENDER", "Done sending " + message);
            } else {
                log("SENDER", "Failed sending " + message);
                throw new RuntimeException();
            }
        }
    }
}
