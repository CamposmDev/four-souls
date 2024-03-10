package org.camposmdev.client.app;

import com.almasb.fxgl.app.*;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.camposmdev.client.model.D6AnimationComponent;
import org.camposmdev.client.model.GameFactory;
import org.camposmdev.client.model.Log;
import org.camposmdev.model.Timex;
import org.camposmdev.model.json.DeckAtlas;
import org.camposmdev.model.json.ImageDataAtlas;

import java.util.concurrent.atomic.AtomicBoolean;

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
//        settings.setIntroEnabled(true);
//        settings.setMainMenuEnabled(true);
//        settings.setGameMenuEnabled(true);
//        settings.setSceneFactory(new FSSceneFactory());
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
//        getAssetLoader().loadVideo("kickstarter_trailer.mp4");
//        getAssetLoader().loadMusic("03 The Binding of Isaac.mp3");
//        getAssetLoader().loadTexture("spritesheets/nightmare.png");
//        getAssetLoader().loadTexture("spritesheets/loading.png");
        getAssetLoader().loadTexture("board.jpg");
        getSettings().setGlobalMusicVolume(0.1);
        getSettings().setGlobalSoundVolume(0.1);
//        loadDeck();
        Log.debugf("Finished loading assets (%s)\n", timer.stop());
    }

    @Override
    protected void initUI() {
        var button1 = getUIFactoryService().newButton("Button1");
        var button2 = getUIFactoryService().newButton("Button2");
        var box = new HBox(10, button1, button2);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("""
                -fx-background-color: rgba(0,0,0,0.5);
                """);
        box.setPrefWidth(getAppWidth());
        box.setPrefHeight(100);
        double[] vertices = {
                40, 50,     // Top left corner
                260, 50,    // Top right corner
                200, 80,   // Bottom right corner
                100, 80    // Bottom left corner
        };
        var poly = new javafx.scene.shape.Polygon(vertices);
        poly.setFill(Color.BLACK);
        poly.setOpacity(0.5);
        var flag = new AtomicBoolean(true);
        var header = new VBox(box, poly);
        header.setAlignment(Pos.CENTER);
        poly.setOnMouseClicked(e -> {
            if (flag.get()) {
                animationBuilder().duration(Duration.millis(250)).onFinished(() -> flag.set(false)).translate(header).to(new Point2D(0, 0)).buildAndPlay();
            } else {
                animationBuilder().duration(Duration.millis(250)).onFinished(() -> flag.set(true)).translate(header).to(new Point2D(0, -100)).buildAndPlay();
            }
        });
        addUINode(header, 0, -100);
    }



    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameFactory());
        final var X_BORDER_MARGIN = 16;
        final var Y_BORDER_MARGIN = 16;
        final var Y_MID_OFFSET = 12;
//        FXGL.loopBGM("The Binding of Isaac - 11 Repentant.mp3");
        var background = texture("board.jpg");
        background.setFitWidth(getSettings().getWidth());
        background.setFitHeight(getSettings().getHeight());
        getGameScene().addGameView(new GameView(background, -1));
        var isaac = loadCardTexture("cards/character/b-isaac.png");
        var treasureCardBack = loadCardTexture("cards/TreasureCardBack.png");
        var lootCardBack = loadCardTexture("cards/LootCardBack.png");
        var monsterCardBack = loadCardTexture("cards/MonsterCardBack.png");

        var butterBean = loadCardTexture("cards/loot/butter/b2-butter_bean.png");
        var beanEntity = new EntityBuilder().at(getAppWidth()/2d - butterBean.getFitWidth()/2d,
                getAppHeight()/2d + Y_MID_OFFSET - butterBean.getFitHeight()/2d)
                .view(butterBean).build();
        var treasureEntity = new EntityBuilder().at(X_BORDER_MARGIN,
                getAppHeight()/2d + Y_MID_OFFSET - treasureCardBack.getFitHeight()/2d).view(treasureCardBack).buildAndAttach();
        var lootCardEntity = new EntityBuilder().at(
                getAppWidth()/2d - lootCardBack.getFitWidth()/2d,
                getAppHeight()/2d + Y_MID_OFFSET - lootCardBack.getFitHeight()/2d)
                .view(lootCardBack).buildAndAttach();
        lootCardEntity.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            var r = rotateOut(lootCardBack);
            r.play();
            r.setOnFinished(e1 -> {
                System.out.println("finished");
                getGameWorld().addEntity(beanEntity);
                var r1 = rotateIn(butterBean);
                r1.play();
            });
        });
        var monsterEntity = new EntityBuilder().at(
                getAppWidth() - monsterCardBack.getFitWidth() - X_BORDER_MARGIN,
                getAppHeight()/2d + Y_MID_OFFSET - monsterCardBack.getFitHeight()/2d).view(monsterCardBack).buildAndAttach();
        var playerEntity = new EntityBuilder().at(X_BORDER_MARGIN, getAppHeight() - isaac.getFitHeight() - Y_BORDER_MARGIN).view(isaac).buildAndAttach();

        addScaleOnMouseHover(playerEntity, ScaleFrom.BOTTOM_LEFT);

        var entity = getGameWorld().spawn("d6");
        entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            entity.getComponent(D6AnimationComponent.class).roll().onSuccess(x -> {
                System.out.println(x);
            });
        });
        entity.translate(new Point2D(-16, -16));
//        var entity = new EntityBuilder().view("d6_red.png").buildAndAttach();
//        entity.setScaleUniform(5);
    }

    enum ScaleFrom {
        TOP_LEFT, TOP_CENTER, TOP_RIGHT,
        CENTER_LEFT, CENTER, CENTER_RIGHT,
        BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT;
    }

    private void addScaleOnMouseHover(Entity entity, ScaleFrom type) {
        var lst = entity.getViewComponent().getChildren();
        if (lst.size() == 0) return;
        if (!(lst.get(0) instanceof ImageView)) return;
        var width = ((ImageView) lst.get(0)).getFitWidth();
        var height = ((ImageView) lst.get(0)).getFitHeight();
        switch (type) {
            case TOP_LEFT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setScaleUniform(1);
                });
            }
            case TOP_CENTER -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width/2d);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width/2d);
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
                    entity.setY(entity.getY() - height/2);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setY(entity.getY() + height/2);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width/2d);
                    entity.setY(entity.getY() - height/2d);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width/2d);
                    entity.setY(entity.getY() + height/2d);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER_RIGHT -> {
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    entity.setX(entity.getX() - width);
                    entity.setY(entity.getY() - height/2d);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width);
                    entity.setY(entity.getY() + height/2d);
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
                    entity.setX(entity.getX() - width/2d);
                    entity.setY(entity.getY() - height);
                    entity.setScaleUniform(2);
                });
                entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                    entity.setX(entity.getX() + width/2d);
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

    private Texture loadCardTexture(String src) {
//        final var FIT_WIDTH = 154;
//        final var FIT_HEIGHT = 210;
        final var FIT_WIDTH = 120;
        final var FIT_HEIGHT = 176;
        var iv = texture(src);
        iv.setFitWidth(FIT_WIDTH);
        iv.setFitHeight(FIT_HEIGHT);
        iv.setEffect(new DropShadow(12, 8, 12, Color.BLACK));
        return iv;
    }

    private void loadDeck() {
        var deck = loadJSON("json/cards/cards.json", DeckAtlas.class);
        var characters = loadJSON(deck.character(), ImageDataAtlas.class);
        characters.images().values().forEach(data -> getAssetLoader().loadTexture(data.source2()));
//        deck.eternal().forEach(x -> loadCards(x));
//        deck.treasure().forEach(x -> loadCards(x));
//        deck.monster().forEach(x -> loadCards(x));
//        deck.loot().forEach(x -> loadCards(x));
//        deck.money().forEach(x -> loadCards(x));
//        deck.bsoul().forEach(x -> loadCards(x));
//        deck.room().forEach(x -> loadCards(x));
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


