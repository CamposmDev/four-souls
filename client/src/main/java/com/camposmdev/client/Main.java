package com.camposmdev.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var pane = new Pane(new Label("hello world"));
        Scene scene = new Scene(pane, 300, 300);
        stage.setScene(scene);
        stage.show();
    }
}
