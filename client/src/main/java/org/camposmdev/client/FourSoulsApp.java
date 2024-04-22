package org.camposmdev.client;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.client.ui.view.GameBoardView;
import org.camposmdev.client.service.BoardPosition;
import org.camposmdev.client.entity.factory.GameBoardFactory;
import org.camposmdev.client.ui.scene.FSSceneFactory;
import org.camposmdev.client.ui.view.TopDrawerView;
import org.camposmdev.util.FXUtil;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class FourSoulsApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }
    static final int APP_WIDTH = 1600, APP_HEIGHT = 900;
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
        options.addEngineService(EntityService.class);
    }

    @Override
    protected void onPreInit() {
        final double VOLUME = 0.1;
        getSettings().setGlobalMusicVolume(VOLUME);
        getSettings().setGlobalSoundVolume(VOLUME);
        getNotificationService().setBackgroundColor(Color.web("#2D2D30"));
        getNotificationService().setTextColor(Color.WHITE);
        getAssetLoader().loadTexture("board.jpg");
    }

    @Override
    protected void initUI() {
        TopDrawerView topDrawer = new TopDrawerView();
        topDrawer.render();
    }

    @Override
    protected void initGame() {
        loopBGM("The Binding of Isaac - 11 Repentant.mp3");
        getGameWorld().addEntityFactory(new GameBoardFactory());
        var es = getService(EntityService.class);
        var board = new GameBoardView(-500);
        board.render();

        /* add treasure deck to game world */
        var treasureEntity = getGameWorld().spawn("treasure_back");
        es.map(treasureEntity, BoardPosition.CENTER_LEFT);

        /* add loot deck to game world */
        var lootEntity = getGameWorld().spawn("loot_back");
        es.map(lootEntity, BoardPosition.CENTER);

        /* add monster deck to game world */
        var monsterEntity = getGameWorld().spawn("monster_back");
        es.map(monsterEntity, BoardPosition.CENTER_RIGHT);

        /* add player to game world */
        var playerEntity = getGameWorld().spawn("player");
        es.map(playerEntity, BoardPosition.BOTTOM_LEFT);

        var d6 = getGameWorld().spawn("d6");
        es.map(d6, BoardPosition.BOTTOM_RIGHT);
        /* add butter bean to game world */
//        var butterBean = loadCard("cards/loot/butter/b2-butter_bean.png");
//        var beanEntity = new EntityBuilder().view(butterBean).build();
//        mapper.map(beanEntity, BoardPosition.CENTER);
    }

    @Deprecated
    private RotateTransition rotateOut(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(90);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }

    @Deprecated
    private RotateTransition rotateIn(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(90);
        rotator.setToAngle(0);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }
}


