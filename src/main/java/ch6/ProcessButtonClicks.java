package ch6;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Scanner;

public class ProcessButtonClicks extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView<String> listView = new ListView<>();
        Button refreshButton = new Button("REFRESH");

        JavaFxObservable.actionEventsOf(refreshButton)
                .observeOn(Schedulers.io())
                .flatMapSingle(a -> Observable.fromArray(
                        getResponse("https://goo.gl/S0xuOi").split("\\r?\\n")).toList())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> listView.getItems().setAll(list));

        VBox root = new VBox();
        root.getChildren().addAll(listView, refreshButton);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private static String getResponse(String path) {
        try {
            return new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
