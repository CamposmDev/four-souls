package org.camposmdev.client.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

public class FX {
    public static Parent view(String src) {
        try {
            URL url = FX.class.getClassLoader().getResource("view/" + src);
            return FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(new Text("Failed to load " + src));
        }
    }

    public static Parent view(String src, Object controller) {
        try {
            URL url = FX.class.getClassLoader().getResource("view/" + src);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(controller);
            fxmlLoader.setLocation(url);
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(new Text("Failed to load " + src));
        }
    }

    public static FXMLLoader load(String src) {
        try {
            URL url = FX.class.getClassLoader().getResource("./view/" + src);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            return fxmlLoader;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
