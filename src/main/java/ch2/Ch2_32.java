package ch2;

import io.reactivex.rxjava3.core.Completable;

public class Ch2_32 {

    public static void main(String[] args) {
        Completable.fromRunnable(() -> System.out.println("Processing..."))
                .subscribe(() -> System.out.println("Done"))
                .dispose();
    }
}
