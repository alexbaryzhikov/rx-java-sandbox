package ch3;


import io.reactivex.rxjava3.core.Observable;

public class Ch3_66 {
    public static void main(String[] args) {
        Observable.empty()
                .single("Bite")
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
