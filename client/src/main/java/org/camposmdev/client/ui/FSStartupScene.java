package org.camposmdev.client.ui;

import com.almasb.fxgl.app.scene.StartupScene;
import com.almasb.fxgl.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

public class FSStartupScene extends StartupScene {

    public FSStartupScene(int appWidth, int appHeight) {
        super(appWidth, appHeight);
        var bg = (StackPane) FXUtil.loadFXML("Startup.fxml");
        assert bg != null;
        bg.setPrefWidth(appWidth);
        bg.setPrefHeight(appHeight);
        bg.setCursor(Cursor.NONE);
        getContentRoot().getChildren().add(bg);
    }
}
