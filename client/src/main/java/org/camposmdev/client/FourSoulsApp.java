package org.camposmdev.client;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.camposmdev.client.game.FSGameFactory;
import org.camposmdev.client.game.component.D6AnimationComponent;
import org.camposmdev.client.ui.FSSceneFactory;
import org.camposmdev.client.ui.TopDrawerView;
import org.camposmdev.util.FXUtil;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class FourSoulsApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings options) {
        final int WIDTH = 1600, HEIGHT = 900;
        options.setApplicationMode(ApplicationMode.DEVELOPER);
        options.setTitle("Four Souls");
        options.setVersion("1.0.0");
        options.setAppIcon("icons/soul_circle.png");
        options.setWidth(WIDTH);
        options.setHeight(HEIGHT);
        options.setPreserveResizeRatio(true);
        options.setScaleAffectedOnResize(true);
        if (options.getApplicationMode() == ApplicationMode.RELEASE) {
            options.setIntroEnabled(true);
            options.setMainMenuEnabled(true);
            options.setGameMenuEnabled(true);
            options.setSceneFactory(new FSSceneFactory());
        }
        options.setManualResizeEnabled(true);
        options.setFullScreenAllowed(true);
        options.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        options.setFontGame("EdmundMcMillen_v2.ttf");
    }

    @Override
    protected void onPreInit() {
        final double VOLUME = 0.1;
        getNotificationService().setBackgroundColor(Color.web("#121212"));
        getNotificationService().setTextColor(Color.WHITE);
        getAssetLoader().loadTexture("board.jpg");
        getSettings().setGlobalMusicVolume(VOLUME);
        getSettings().setGlobalSoundVolume(VOLUME);
    }

    @Override
    protected void initUI() {
        TopDrawerView topDrawer = new TopDrawerView();
        topDrawer.render();
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new FSGameFactory());
        final var X_BORDER_MARGIN = 16;
        final var Y_BORDER_MARGIN = 16;
        final var Y_MID_OFFSET = 12;
        var background = texture("board.jpg");
        background.setFitWidth(getAppWidth());
        background.setFitHeight(getAppHeight());
        getGameScene().addGameView(new GameView(background, -1));
        var treasureCardBack = loadCard("cards/TreasureCardBack.png");
        var lootCardBack = FXUtil.loadCard("cards/LootCardBack.png");
        var monsterCardBack = loadCard("cards/MonsterCardBack.png");

        var butterBean = loadCard("cards/loot/butter/b2-butter_bean.png");
        var beanEntity = new EntityBuilder().at(getAppWidth() / 2d - butterBean.getFitWidth() / 2d,
                        getAppHeight() / 2d + Y_MID_OFFSET - butterBean.getFitHeight() / 2d)
                .view(butterBean).build();
        /* add treasure deck to game world */
        var treasureEntity = new EntityBuilder().at(X_BORDER_MARGIN,
                getAppHeight() / 2d + Y_MID_OFFSET - treasureCardBack.getFitHeight() / 2d).view(treasureCardBack).build();
        getGameWorld().addEntity(treasureEntity);
        /* add loot deck to game world */
        var lootEntity = new EntityBuilder().at(
                        getAppWidth() / 2d - lootCardBack.getFitWidth() / 2d,
                        getAppHeight() / 2d + Y_MID_OFFSET - lootCardBack.getFitHeight() / 2d)
                .view(lootCardBack).buildAndAttach();
        lootEntity.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            var r = rotateOut(lootCardBack);
            r.play();
            r.setOnFinished(e1 -> {
                var r1 = rotateIn(butterBean);
                r1.play();
                getGameWorld().addEntity(beanEntity);
            });
        });
        var monsterEntity = new EntityBuilder().at(
                getAppWidth() - monsterCardBack.getFitWidth() - X_BORDER_MARGIN,
                getAppHeight() / 2d + Y_MID_OFFSET - monsterCardBack.getFitHeight() / 2d).view(monsterCardBack).build();
        getGameWorld().addEntity(monsterEntity);

        var playerEntity = getGameWorld().spawn("player");
        getGameWorld().addEntity(playerEntity);
        addScaleOnMouseClick(playerEntity, ScaleFrom.BOTTOM_LEFT);
        addScaleOnMouseHover(playerEntity, ScaleFrom.BOTTOM_LEFT);

        var entity = getGameWorld().spawn("d6");
        entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
                entity.getComponent(D6AnimationComponent.class).roll().onSuccess(x -> {

                }));
        entity.translate(new Point2D(-16, -16));
//        var entity = new EntityBuilder().view("d6_red.png").buildAndAttach();
//        entity.setScaleUniform(5);
    }

    private void addScaleOnMouseClick(Entity entity, ScaleFrom type) {
        var lst = entity.getViewComponent().getChildren();
        if (lst.isEmpty()) return;
        if (!(lst.getFirst() instanceof ImageView)) return;
        var width = ((ImageView) lst.getFirst()).getFitWidth();
        var height = ((ImageView) lst.getLast()).getFitHeight();
        var scale = new AtomicBoolean(true);
        switch (type) {
            case BOTTOM_LEFT -> {
                entity.getViewComponent().addOnClickHandler(e -> {
                    if (scale.get()) {
                        entity.setY(entity.getY() - height);
                        entity.setScaleUniform(2);
                    } else {
                        entity.setY(entity.getY() + height);
                        entity.setScaleUniform(1);
                    }
                    scale.set(!scale.get());
                });
            }
        }
    }

    enum ScaleFrom {
        TOP_LEFT, TOP_CENTER, TOP_RIGHT,
        CENTER_LEFT, CENTER, CENTER_RIGHT,
        BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT
    }

    private void addScaleOnMouseHover(Entity entity, ScaleFrom type) {
        var lst = entity.getViewComponent().getChildren();
        if (lst.isEmpty()) return;
        if (!(lst.getFirst() instanceof ImageView)) return;
        var width = ((ImageView) lst.getFirst()).getFitWidth();
        var height = ((ImageView) lst.getFirst()).getFitHeight();
        switch (type) {
            case TOP_LEFT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> entity.setScaleUniform(2));
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> entity.setScaleUniform(1));
            }
            case TOP_CENTER -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width / 2d);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width / 2d);
                    entity.setScaleUniform(1);
                });
            }
            case TOP_RIGHT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER_LEFT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setY(entity.getY() - height / 2);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setY(entity.getY() + height / 2);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width / 2d);
                    entity.setY(entity.getY() - height / 2d);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width / 2d);
                    entity.setY(entity.getY() + height / 2d);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER_RIGHT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width);
                    entity.setY(entity.getY() - height / 2d);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width);
                    entity.setY(entity.getY() + height / 2d);
                    entity.setScaleUniform(1);
                });
            }
            case BOTTOM_LEFT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setY(entity.getY() - height);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setY(entity.getY() + height);
                    entity.setScaleUniform(1);
                });
            }
            case BOTTOM_CENTER -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width / 2d);
                    entity.setY(entity.getY() - height);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width / 2d);
                    entity.setY(entity.getY() + height);
                    entity.setScaleUniform(1);
                });
            }
            case BOTTOM_RIGHT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width);
                    entity.setY(entity.getY() - height);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width);
                    entity.setY(entity.getY() + height);
                    entity.setScaleUniform(1);
                });
            }
        }
    }

    private RotateTransition rotateOut(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(90);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }

    private RotateTransition rotateIn(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(90);
        rotator.setToAngle(0);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }

    private Texture loadCard(String src) {
        final var FIT_WIDTH = 120;
        final var FIT_HEIGHT = 176;
        var iv = texture(src);
        iv.setFitWidth(FIT_WIDTH);
        iv.setFitHeight(FIT_HEIGHT);
        iv.setEffect(new DropShadow(12, 8, 12, Color.BLACK));
        return iv;
    }
}


