package org.camposmdev.client.ui;

import javafx.scene.layout.StackPane;

public class FSStartupScene extends com.almasb.fxgl.app.scene.StartupScene {

    public FSStartupScene(int appWidth, int appHeight) {
        super(appWidth, appHeight);
        var bg = (StackPane) FXUtil.loadFXML("Startup.fxml");
        assert bg != null;
        bg.setPrefWidth(appWidth);
        bg.setPrefHeight(appHeight);
        getContentRoot().getChildren().add(bg);
    }
}
