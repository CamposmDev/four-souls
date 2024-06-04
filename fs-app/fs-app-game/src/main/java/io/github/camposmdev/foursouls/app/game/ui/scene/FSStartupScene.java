package io.github.camposmdev.foursouls.app.game.ui.scene;

import com.almasb.fxgl.app.scene.StartupScene;
import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import io.github.camposmdev.foursouls.model.fx.FXUtil;

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
