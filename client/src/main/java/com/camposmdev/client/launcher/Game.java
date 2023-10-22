package com.camposmdev.client.launcher;

import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.addUINode;

public class Game extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.set3D(true);
        settings.setAppIcon("icon-soul-circle.png");
        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setVersion("1.0");
        settings.setTitle("Four Souls Online");
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setFullScreenAllowed(true);
        settings.setFontGame("EdmundMcMillen_v2.ttf");
    }

    @Override
    protected void onPreInit() {
        FXGL.loopBGM("menu.mp3");
        FXGL.getNotificationService().setBackgroundColor(Color.BLACK);
        FXGL.getNotificationService().setTextColor(Color.WHITE);
    }

    @Override
    protected void initUI() {
        Platform.runLater(() -> {
            var name = Game.class.getClassLoader().getResource("./assets/ui/background/index.html").toExternalForm();
            WebView web = new WebView();
            web.setPrefWidth(getSettings().getWidth());
            web.setPrefHeight(getSettings().getHeight());
            web.getEngine().load(name);
            addUINode(web);
//            FXGL.animationBuilder().fadeIn(web).buildAndPlay();
            URL url = Game.class.getClassLoader().getResource("assets/ui/fxml/Login.fxml");
            try {
                AnchorPane root = FXMLLoader.load(url);
                addUINode(root, (getSettings().getWidth()/2d - root.getPrefWidth()/2d), (getSettings().getHeight()/2d - root.getPrefHeight()/2d));
                FXGL.animationBuilder().delay(Duration.millis(1500)).fadeIn(root).buildAndPlay();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

