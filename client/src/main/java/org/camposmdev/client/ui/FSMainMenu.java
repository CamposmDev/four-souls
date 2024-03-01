package org.camposmdev.client.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.camposmdev.client.ui.controllers.LoginController;
import org.camposmdev.client.net.API;

public class FSMainMenu extends FXGLMenu {
    public FSMainMenu(MenuType type) {
        super(type);
        Platform.runLater(() -> {
            FXGL.getPrimaryStage().setOnCloseRequest(e -> API.get().close());
            var loginUI = FXUtil.loadUI("Login.fxml");
            assert loginUI != null;
            ((LoginController) loginUI.getController()).setParent(getContentRoot());
            WebView background = FXUtil.loadBG();
            background.setPrefWidth(getAppWidth());
            background.setPrefHeight(getAppHeight());
            var root = new StackPane(background, loginUI.getRoot());
            getContentRoot().getChildren().addAll(root);
        });
    }
}
