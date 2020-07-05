package ch9;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observers.JavaFxObserver;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class BindUi extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Label label = new Label();
        label.relocate(16, 16);
        root.getChildren().addAll(label);

        Scene scene = new Scene(root, 320, 180);
        stage.setScene(scene);
        stage.show();

        Binding<String> seconds = Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(Object::toString)
                .observeOn(JavaFxScheduler.platform())
                .to(JavaFxObserver::toBinding);

        label.textProperty().bind(seconds);
    }
}
