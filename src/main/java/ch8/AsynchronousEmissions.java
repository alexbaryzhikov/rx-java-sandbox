package ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static util.Utils.log;
import static util.Utils.sleep;

public class AsynchronousEmissions {

    public static void main(String[] args) {
        Flowable.range(1, Integer.MAX_VALUE)
                .subscribeOn(Schedulers.newThread())
                .map(Item::new)
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    sleep(10);
                    log("RECV", it);
                });
        sleep(Integer.MAX_VALUE);
    }

    static final class Item {
        final int id;

        public Item(int id) {
            this.id = id;
            log("ITEM", "Construct " + id);
        }

        @Override
        public String toString() {
            return "Item " + id;
        }
    }
}
