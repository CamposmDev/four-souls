package org.camposmdev.client.ui.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.util.Duration;
import org.camposmdev.client.entity.EntityType;
import org.camposmdev.client.entity.component.player.CharacterComponent;
import org.camposmdev.client.entity.component.player.PlayerLootComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class ActionDrawerView implements View {
    private static final double BT_BOX_WIDTH = getAppWidth();
    private static final double BT_BOX_HEIGHT = 65;
    private static final double POLY_HEIGHT = 25;
    private static final int SPACING = 8;
    private static final float OPACITY = 0.75f;
    private boolean hidden;
    private final VBox root;
    private HBox btBox;
    private final Button btDraw;
    private Button btShop, btAttack, btEndTurn;

    public ActionDrawerView() {
        btDraw = createButton("Draw", event -> {
            var entity = getGameWorld().getSingleton(EntityType.PLAYER);
            entity.getComponentOptional(PlayerLootComponent.class)
                    .ifPresent(PlayerLootComponent::draw);
        });
        btShop = createButton("Shop", event -> {
            /* TODO - Implement shop system */
            var btCancel = createButton("Cancel", event1 -> {
                btBox.getChildren().clear();
                btBox.getChildren().addAll(btDraw, btShop, btAttack, btEndTurn);
            });
            btBox.getChildren().clear();
            btBox.getChildren().add(btCancel);
            getNotificationService().pushNotification("Coming soon!");
        });
        btAttack = createButton("Attack", event -> {
            /* TODO - Implement combat system */
            getGameScene().setCursor(new ImageCursor(image("cursor_sword.png")));
            var btCancel = createButton("Cancel", event1 -> {
                btBox.getChildren().clear();
                btBox.getChildren().addAll(btDraw, btShop, btAttack, btEndTurn);
                var image = image(getSettings().getDefaultCursor().getImageName());
                getGameScene().setCursor(new ImageCursor(image));
            });
            btBox.getChildren().clear();
            btBox.getChildren().add(btCancel);
            getNotificationService().pushNotification("Coming soon!");
        });
        btEndTurn = createButton("End Turn", event -> {
            /* TODO - Implement turn system */
            var entity = getGameWorld().getSingleton(EntityType.PLAYER);
            entity.getComponentOptional(CharacterComponent.class).ifPresent(comp -> {
                comp.damage(1);
            });
        });
        /* init button box container */
        btBox = new HBox(SPACING, btDraw, btShop, btAttack, btEndTurn);
        btBox.setAlignment(Pos.CENTER);
        btBox.setStyle(String.format("-fx-background-color: rgba(0,0,0,%f);", OPACITY));
        btBox.setPrefWidth(BT_BOX_WIDTH);
        btBox.setPrefHeight(BT_BOX_HEIGHT);
        /* init poly drawer handle */
        double[] points = {
                105, 25,    // Top left corner
                195, 25,   // Top right corner
                230, 50,    // Bottom right corner
                70, 50,     // Bottom left corner
        };
        var poly = new Polygon(points);
        poly.setStrokeLineJoin(StrokeLineJoin.ROUND);
        poly.setFill(Color.BLACK);
        poly.setOpacity(OPACITY);
        /* init drawer line details */
        var l1 = new Line(0, 0, 8, 0);
        l1.setStroke(Color.WHITE);
        l1.setStrokeWidth(2);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        var l2 = new Line(0, 0, 8, 0);
        l2.setStroke(Color.WHITE);
        l2.setStrokeWidth(2);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        /* wrap line details into a container */
        var txt = getUIFactoryService().newText("Actions", Color.WHITE, 14);
        var hb = new HBox((SPACING), l1, txt, l2);
        hb.setAlignment(Pos.CENTER);
        /* wrap poly and line details into a container */

        StackPane handle = new StackPane(poly, hb);
        root = new VBox(handle, btBox);
        root.setAlignment(Pos.CENTER);
        /* add event handlers */
        txt.setOnMouseEntered(event -> animationBuilder()
                .duration(Duration.millis(250))
				.onFinished(() -> hidden = false)
				.translate(root)
				.to(new Point2D(0, getAppHeight()-BT_BOX_HEIGHT-POLY_HEIGHT))
				.buildAndPlay());
        txt.setOnMouseExited(event -> {
            if (!btBox.isHover() && !hidden) {
                animationBuilder().duration(Duration.millis(250))
                        .onFinished(() -> hidden = true)
                        .translate(root)
                        .to(new Point2D(0, getAppHeight()-POLY_HEIGHT))
                        .buildAndPlay();
            }
        });
        btBox.setOnMouseExited(event -> {
            if (!txt.isHover() && !btBox.isHover() && !hidden) {
                animationBuilder().duration(Duration.millis(250))
                        .onFinished(() -> hidden = true)
                        .translate(root)
                        .to(new Point2D(0, getAppHeight()-POLY_HEIGHT))
                        .buildAndPlay();
            }
        });
    }

    @Override
    public void render() {
        addUINode(root, 0, getAppHeight()-POLY_HEIGHT);
    }

    @Override
    public void dispose() {
        removeUINode(root);
    }

    @Override
    public Node content() {
        return root;
    }

    private Button createButton(String text, EventHandler<ActionEvent> action) {
        var bt = getUIFactoryService().newButton(text);
        bt.setFocusTraversable(false);
        bt.setOnAction(action);
        return bt;
    }

    private static ActionDrawerView singleton;

    public static ActionDrawerView instance() {
        if (singleton == null)
            singleton = new ActionDrawerView();
        return singleton;
    }
}
