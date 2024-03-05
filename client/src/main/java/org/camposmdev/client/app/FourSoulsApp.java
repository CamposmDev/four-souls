package org.camposmdev.client.app;

import com.almasb.fxgl.app.*;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.paint.Color;
import org.camposmdev.client.model.Log;
import org.camposmdev.client.ui.FSSceneFactory;
import org.camposmdev.model.Timex;
import org.camposmdev.model.json.DeckAtlas;
import org.camposmdev.model.json.ImageDataAtlas;

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
    }

    @Override
    protected void onPreInit() {
        var timer = new Timex().start();
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
        loadDeck();
        Log.debugf("Finished loading assets (%s)\n", timer.stop());
    }

    private void loadDeck() {
        var deck = loadJSON("json/cards/cards.json", DeckAtlas.class);
        var characters = loadJSON(deck.character(), ImageDataAtlas.class);
        characters.images().values().forEach(data -> getAssetLoader().loadTexture(data.source2()));
        deck.eternal().forEach(x -> loadCards(x));
        deck.treasure().forEach(x -> loadCards(x));
        deck.monster().forEach(x -> loadCards(x));
        deck.loot().forEach(x -> loadCards(x));
        deck.money().forEach(x -> loadCards(x));
        deck.bsoul().forEach(x -> loadCards(x));
        deck.room().forEach(x -> loadCards(x));
    }

    private void loadCards(String src) {
        var atlas = loadJSON(src, ImageDataAtlas.class);
        atlas.images().values().forEach(data -> {
            getAssetLoader().loadTexture(data.source2());
        });
    }

    private <T> T loadJSON(String src, Class<T> type) {
        var result = getAssetLoader().loadJSON(src, type);
        assert result.isPresent();
        return result.get();
    }
}


