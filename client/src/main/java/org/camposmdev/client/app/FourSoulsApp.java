package org.camposmdev.client.app;

import com.almasb.fxgl.app.*;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.paint.Color;
import org.camposmdev.client.model.Log;
import org.camposmdev.client.ui.FSSceneFactory;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class FourSoulsApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Four Souls");
        settings.setVersion("1.0");
        settings.setAppIcon("icons/soul_circle.png");
        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setPreserveResizeRatio(true);
        settings.setScaleAffectedOnResize(true);
        settings.setIntroEnabled(true);
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setSceneFactory(new FSSceneFactory());
        settings.setManualResizeEnabled(true);
        settings.setFullScreenAllowed(true);
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setFontGame("EdmundMcMillen_v2.ttf");
        settings.setApplicationMode(ApplicationMode.DEBUG);
        Log.debug("it works??????????????????????");
    }

    @Override
    protected void onPreInit() {
        getNotificationService().setBackgroundColor(Color.web("#121212"));
        getNotificationService().setTextColor(Color.WHITE);
        getAssetLoader().loadVideo("kickstarter_trailer.mp4");
        getAssetLoader().loadMusic("03 The Binding of Isaac.mp3");
        getAssetLoader().loadTexture("spritesheets/nightmare.png");
        getAssetLoader().loadTexture("spritesheets/loading.png");
        if (getSettings().getApplicationMode().equals(ApplicationMode.DEBUG)) {
            getSettings().setGlobalMusicVolume(0.0);
            getSettings().setGlobalSoundVolume(0.1);
        }
    }
}


