package org.camposmdev.client.app;

import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.core.asset.AssetType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.camposmdev.client.net.Client;

import java.io.IOException;
import java.net.URL;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.addUINode;

public class ClientApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setAppIcon("icons/icon-soul-circle.png");
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
        var music = FXGL.loopBGM("03 The Binding of Isaac.mp3");
        music.getAudio().setVolume(0.1);
        FXGL.getNotificationService().setBackgroundColor(Color.web("#121212"));
        FXGL.getNotificationService().setTextColor(Color.WHITE);
    }

    @Override
    protected void initUI() {
        Platform.runLater(() -> {
            var resource = ClientApp.class.getClassLoader().getResource("./assets/ui/background/index.html");
            assert resource != null : "Failed to load resource";
            WebView web = new WebView();
            web.setPrefWidth(getSettings().getWidth());
            web.setPrefHeight(getSettings().getHeight());
            web.getEngine().load(resource.toExternalForm());
            web.setDisable(true);
            addUINode(web);
            URL url = ClientApp.class.getClassLoader().getResource("assets/ui/fxml/Login.fxml");
            assert url != null : "Failed to get Login.fxml";
            try {
                AnchorPane root = FXMLLoader.load(url);
                addUINode(root, (getSettings().getWidth()/2d - root.getPrefWidth()/2d), (getSettings().getHeight()/2d - root.getPrefHeight()/2d));
                animationBuilder().delay(Duration.millis(1500)).fadeIn(root).buildAndPlay();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.W, () -> System.out.println("W key is pressed"));
    }

    @Override
    protected void onUpdate(double tpf) {
        if (Client.getInstance().isLoggedIn()) {

        }
    }
}


