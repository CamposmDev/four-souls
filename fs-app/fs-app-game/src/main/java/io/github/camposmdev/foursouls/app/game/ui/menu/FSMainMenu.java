package io.github.camposmdev.foursouls.app.game.ui.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import io.github.camposmdev.foursouls.app.game.api.API;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import io.github.camposmdev.foursouls.app.game.ui.controllers.LoginController;
import io.github.camposmdev.foursouls.model.fx.FXUtil;


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
    public void onCreate() {
        super.onCreate();
        FXGL.getAudioPlayer().stopAllMusic();
        FXGL.loopBGM("03 The Binding of Isaac.mp3");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FXGL.getAudioPlayer().stopAllMusic();
    }
}
