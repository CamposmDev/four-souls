package io.github.camposmdev.foursouls.app.editor.ui;

import com.almasb.fxgl.app.scene.StartupScene;
import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import io.github.camposmdev.foursouls.model.ui.FXUtil;

public class EditorStartupScene extends StartupScene {
    public EditorStartupScene(int width, int height) {
        super(width, height);
        var bg = (StackPane) FXUtil.loadFXML("Startup.fxml");
        assert bg != null;
        bg.setPrefWidth(width);
        bg.setPrefHeight(height);
        bg.setCursor(Cursor.NONE);
        getContentRoot().getChildren().add(bg);
    }
}
