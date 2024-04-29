package org.camposmdev.client;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.camposmdev.client.entity.factory.FSEntityFactory;
import org.camposmdev.client.model.Game;
import org.camposmdev.client.service.BoardPosition;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.client.ui.scene.FSSceneFactory;
import org.camposmdev.client.ui.view.ActionDrawerView;
import org.camposmdev.client.ui.view.PlayMatView;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class FourSoulsApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }
    private static final int APP_WIDTH = 1600, APP_HEIGHT = 900;
    @Override
    protected void initSettings(GameSettings options) {
        options.setApplicationMode(ApplicationMode.DEVELOPER);
        options.setTitle("Four Souls");
        options.setVersion("1.0.0-alpha");
        options.setAppIcon("icons/soul_circle.png");
        options.setWidth(APP_WIDTH);
        options.setHeight(APP_HEIGHT);
        options.setPreserveResizeRatio(true);
        options.setScaleAffectedOnResize(true);
        if (options.getApplicationMode() == ApplicationMode.RELEASE) {
            options.setIntroEnabled(true);
            options.setMainMenuEnabled(true);
        }
        options.setGameMenuEnabled(true);
        options.setSceneFactory(new FSSceneFactory());
        options.setManualResizeEnabled(true);
        options.setFullScreenAllowed(true);
        options.setPauseMusicWhenMinimized(true);
        options.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        options.setFontGame("EdmundMcMillen_v2.ttf");
        options.setFontUI("EdmundMcMillen_v2.ttf");
        options.setFontText("EdmundMcMillen_v2.ttf");
        options.getCSSList().add("main.css");
        options.addEngineService(EntityService.class);
    }

    @Override
    protected void onPreInit() {
        final double VOLUME = 0.1;
        getSettings().setGlobalMusicVolume(VOLUME);
        getSettings().setGlobalSoundVolume(VOLUME);
        getNotificationService().setBackgroundColor(Color.web("#2D2D30"));
        getNotificationService().setTextColor(Color.WHITE);
        /* load texture and store in cache */
        getAssetLoader().loadTexture("board.jpg");
    }

    @Override
    protected void initUI() {
        ActionDrawerView.instance().render();
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        /* if the player chose singleplayer, create a game var */
        var game = Game.create();
        game.shuffle();
        vars.put("game", game);
        /* otherwise send request to server */
    }

    @Override
    protected void initGame() {
        /* start the music */
//        loopBGM("The Binding of Isaac - 11 Repentant.mp3");
        /* add the four souls entity factory to spawn the game entities */
        getGameWorld().addEntityFactory(new FSEntityFactory());
        var es = getService(EntityService.class);
        /* render in the play mat */
        var playMat = new PlayMatView(-500);
        playMat.render();
        /* add treasure deck to game world */
        var treasureEntity = getGameWorld().spawn("treasure_deck");
        es.mapper().set(treasureEntity, BoardPosition.CENTER_LEFT);
        /* add loot deck to game world */
        var lootEntity = getGameWorld().spawn("loot_deck");
        es.mapper().set(lootEntity, BoardPosition.CENTER);
        /* add monster deck to game world */
        var monsterEntity = getGameWorld().spawn("monster_deck");
        es.mapper().set(monsterEntity, BoardPosition.CENTER_RIGHT);
        /* add player to game world */
        es.spawnPlayer("b-isaac");
    }

    @Override
    protected void initInput() {
        /* set key listener to fullscreen game */
        onKeyDown(KeyCode.F11, () -> {
            var fullScreen = getSettings().getFullScreen().get();
            getSettings().getFullScreen().set(!fullScreen);
        });
    }
}


