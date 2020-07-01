package util;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static <T> void log(String tag, T x) {
        System.out.printf("%s [%s] %s %s%n", LocalTime.now(), Thread.currentThread().getName(), tag, x);
    }

    public static <T> T intenseCalculation(T value) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }

    public static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
