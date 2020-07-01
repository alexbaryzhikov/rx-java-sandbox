package ch7;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;

public class Keystrokes extends Application {

    @Override
    public void start(Stage stage) {
        Label typedTextLabel = new Label("");
        typedTextLabel.relocate(20, 30);

        Pane root = new Pane();
        root.getChildren().addAll(typedTextLabel);
        Scene scene = new Scene(root, 100, 100);
        stage.setScene(scene);
        stage.show();

        setObservers(scene, typedTextLabel);
    }

    private void setObservers(Scene scene, Label label) {
        Observable<String> typedLetters =
                JavaFxObservable.eventsOf(scene, KeyEvent.KEY_TYPED)
                        .map(KeyEvent::getCharacter);

        // Signal 1 sec of inactivity
        typedLetters.debounce(1, TimeUnit.SECONDS)
                .startWith("") // trigger initial
                .doOnNext(it -> log("CUTOFF", it))
                .switchMap(it -> typedLetters.scan("", (rolling, next) -> rolling + next))
                .observeOn(JavaFxScheduler.platform())
                .doOnNext(it -> log("LABEL", it))
                .subscribe(label::setText);
    }
}
