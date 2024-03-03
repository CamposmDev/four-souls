package org.camposmdev.client.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import org.camposmdev.client.ui.controllers.LoginController;
import org.camposmdev.client.net.API;
import org.jetbrains.annotations.NotNull;


public class FSMainMenu extends FXGLMenu {
    public FSMainMenu(MenuType type) {
        super(type);
        Platform.runLater(() -> {
            FXGL.getPrimaryStage().setOnCloseRequest(e -> API.get().close());
            var loginUI = FXUtil.loadUI("Login.fxml");
            assert loginUI != null;
            ((LoginController) loginUI.getController()).setParent(getContentRoot());
            var space = FXUtil.loadSpace();
            space.setPrefWidth(getAppWidth());
            space.setPrefHeight(getAppHeight());
            var root = new StackPane();
            root.getChildren().add(space);
            root.getChildren().add(loginUI.getRoot());
            getContentRoot().getChildren().add(root);
        });
    }

    @Override
    public void onEnteredFrom(@NotNull Scene prevState) {
        super.onEnteredFrom(prevState);
        FXGL.loopBGM("03 The Binding of Isaac.mp3");
    }
}
