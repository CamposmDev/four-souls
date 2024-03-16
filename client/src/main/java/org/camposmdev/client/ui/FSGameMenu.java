package org.camposmdev.client.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.camposmdev.client.game.Log;
import org.jetbrains.annotations.NotNull;

public class FSGameMenu extends FXGLMenu {

    public FSGameMenu(@NotNull MenuType type) {
        super(type);
        var bt1 = FXGL.getUIFactoryService().newButton("Resume");
        bt1.setOnAction(e -> this.fireResume());
        var bt2 = FXGL.getUIFactoryService().newButton("Main Menu");
        bt2.setOnAction(e -> this.fireExitToMainMenu());
        var box = new VBox(10, bt1, bt2);
        box.setAlignment(Pos.CENTER);
        var root = new StackPane(box);
        root.setPrefWidth(getAppWidth());
        root.setPrefHeight(getAppHeight());
        addChild(root);
    }

    @Override
    public void onCreate() {
        Log.debug("Creating FSGameMenu!");
    }

    @Override
    public void onExitingTo(@NotNull Scene nextState) {
        Log.debug("Destroying FSGameMenu!");
    }
}
