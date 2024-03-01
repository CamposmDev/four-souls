package org.camposmdev.client.app;

import com.almasb.fxgl.app.*;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import org.camposmdev.client.ui.FSSceneFactory;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class FourSoulsApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setAppIcon("icons/soul_circle.png");
        settings.setWidth(1920);
        settings.setHeight(1080);
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setSceneFactory(new FSSceneFactory());
        settings.setIntroEnabled(false);
        settings.setManualResizeEnabled(true);
        settings.setScaleAffectedOnResize(true);
        settings.setFullScreenAllowed(true);
        settings.setVersion("1.0");
        settings.setTitle("Four Souls");
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setFontGame("EdmundMcMillen_v2.ttf");
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
//        List<String> lst = new LinkedList<>();
//        lst.add("global-chat.css");
//        settings.setCSSList(lst);
    }

    @Override
    protected void onPreInit() {
        var music = FXGL.loopBGM("03 The Binding of Isaac.mp3");
        music.getAudio().setVolume(0.1);
        FXGL.getNotificationService().setBackgroundColor(Color.web("#121212"));
        FXGL.getNotificationService().setTextColor(Color.WHITE);
        getAssetLoader().loadTexture("spritesheets/nightmare.png");
        getAssetLoader().loadTexture("spritesheets/loading.png");

    }
}


