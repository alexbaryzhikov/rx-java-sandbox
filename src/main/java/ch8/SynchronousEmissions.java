package ch8;

import io.reactivex.rxjava3.core.Observable;

import static util.Utils.log;
import static util.Utils.sleep;

public class SynchronousEmissions {

    public static void main(String[] args) {
        Observable.range(1, Integer.MAX_VALUE)
                .map(Item::new)
                .subscribe(it -> {
                    sleep(150);
                    log("RECV", it);
                });
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
