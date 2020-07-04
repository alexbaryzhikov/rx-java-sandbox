package sandbox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static util.Utils.log;
import static util.Utils.sleep;

public class ScheduledExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> log("TICK", ""), 0, 1, TimeUnit.SECONDS);
        sleep(5000);
        executor.shutdown();
        sleep(3000);
    }
}
