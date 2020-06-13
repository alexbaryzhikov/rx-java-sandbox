package ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ch2_13 extends Application {

    @Override
    public void start(Stage primaryStage) {
        ToggleButton toggleButton = new ToggleButton("TOGGLE ME");
        Label label = new Label();
        Button disposeButton = new Button("Dispose");

        final Disposable d = valuesOf(toggleButton.selectedProperty())
                .map(selected -> selected ? "DOWN" : "UP")
                .subscribe(label::setText);
        disposeButton.setOnMouseClicked(event -> d.dispose());

        VBox vBox = new VBox(toggleButton, label, disposeButton);
        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
    }

    private static <T> Observable<T> valuesOf(final ObservableValue<T> fxObservable) {
        return Observable.create(observableEmitter -> {
            observableEmitter.onNext(fxObservable.getValue());
            ChangeListener<T> listener =
                    (observable, oldValue, newValue) -> observableEmitter.onNext(newValue);
            fxObservable.addListener(listener);
            observableEmitter.setCancellable(() -> fxObservable.removeListener(listener));
        });
    }
}
