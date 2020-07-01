package ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FetchResponse {
    public static void main(String[] args) throws InterruptedException {
        String url = "https://api.github.com/users/alexbaryzhikov/starred";
        Observable.fromCallable(() -> getResponse(url))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace
                );
        TimeUnit.SECONDS.sleep(10);
    }

    private static String getResponse(String url) throws IOException {
        return new Scanner(new URL(url).openStream(), "UTF-8")
                .useDelimiter("\\A")
                .next();
    }
}
