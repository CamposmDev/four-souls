package org.camposmdev.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.camposmdev.Launcher;
import org.camposmdev.app.model.CharacterCardBuilderAppModel;

public class CharacterCardBuilderApp extends Application {
    private static final double VERSION = 1.0;
    public static final String TITLE = "Character Builder " + VERSION;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CharacterCardBuilderAppModel.getInstance();
        var src = CharacterCardBuilderApp.class.getClassLoader().getResource("assets/fxml/CharacterBuilderApp.fxml");
        FXMLLoader loader = new FXMLLoader(src);
        stage = loader.load();
        stage.show();
    }
}
