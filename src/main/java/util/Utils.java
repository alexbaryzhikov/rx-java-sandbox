package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Utils {
    private static final DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm:ss.SSS");

    public static <T> void log(String tag, T item) {
        System.out.printf(
                "%s [%s] %s %s%n",
                LocalTime.now().format(f),
                Thread.currentThread().getName(),
                tag,
                item
        );
    }

    public static <T> T intenseCalculation(T value) throws InterruptedException {
        return intenseCalculation(value, ThreadLocalRandom.current().nextLong(3000));
    }

    public static <T> T intenseCalculation(T value, long durationBound) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(durationBound));
        return value;
    }

    public static void sleep(long duration) {
        try {
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (InterruptedException ignored) {
        }
    }

    public static int randomSleepTime(int from, int to) {
        return from + ThreadLocalRandom.current().nextInt(to);
    }
}
