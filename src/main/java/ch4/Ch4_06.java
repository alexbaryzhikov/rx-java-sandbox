package ch4;

import io.reactivex.rxjava3.core.Observable;

public class Ch4_06 {
    public static void main(String[] args) {
        Observable.just("521934/2342/FOXTROT", "21962/12112/TANGO/78886")
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .subscribe(System.out::println);
    }
}
