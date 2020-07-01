package ch7;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

import static util.Utils.log;

public class Stopwatch extends Application {

    @Override
    public void start(Stage stage) {
        Label counterLabel = new Label("");
        counterLabel.relocate(20, 30);
        ToggleButton startStopButton = new ToggleButton();
        startStopButton.relocate(20, 60);
        setObservers(counterLabel, startStopButton);

        Pane root = new Pane();
        root.getChildren().addAll(counterLabel, startStopButton);
        stage.setScene(new Scene(root, 100, 100));
        stage.show();
    }

    private void setObservers(Label label, ToggleButton button) {
        Observable<Boolean> selectedState =
                JavaFxObservable.valuesOf(button.selectedProperty())
                        .publish()
                        .autoConnect(2);
        selectedState
                .switchMap(selected -> {
                    log("SWITCH", selected);
                    return selected ?
                            Observable.interval(1, TimeUnit.MILLISECONDS) :
                            Observable.empty();
                })
                .observeOn(JavaFxScheduler.platform()) // Run on UI thread
                .map(Object::toString)
                .subscribe(label::setText);
        selectedState
                .subscribe(selected -> button.setText(selected ? "STOP" : "START"));
    }
}
