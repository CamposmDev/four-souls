package org.camposmdev.client.controllers;

import com.almasb.fxgl.animation.AnimatedValue;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.FXGL;
import com.sun.media.jfxmediaimpl.HostUtils;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.camposmdev.client.net.Client;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class LoginController {
    @FXML AnchorPane root;
    @FXML TextField tfName, tfPassword;

    public void login() {
        var name = tfName.getText();
        var password = tfPassword.getText();
        if (Client.getInstance().login(name, password)) {
            /* login success, remove login screen */
            FXGL.animationBuilder().duration(Duration.millis(500)).translate(root).to(new Point2D(root.getTranslateX(), root.getHeight()*2)).buildAndPlay();
            FXGL.animationBuilder().onFinished(() -> {
                removeUINode(root);
                showMainMenu();
            }).duration(Duration.millis(500)).fadeOut(root).buildAndPlay();
        }
    }

    private void showMainMenu() {
        final var OFFSET = 8;
        var midX = getSettings().getWidth() / 2d;
        var midY = getSettings().getHeight() / 2d;
        var logo = FXGL.getAssetLoader().loadTexture("logo.png");
        logo.setScaleX(0.5);
        logo.setScaleY(0.5);
        addUINode(logo, midX - logo.getWidth()/2d, -120);
        var lbl1 = initLabel("Singleplayer");
        lbl1.setOnMouseClicked(e -> {
            getGameScene().clearUINodes();
            var shadow = new DropShadow();
            shadow.setColor(Color.BLACK);
            shadow.setRadius(5);
            shadow.setSpread(0.4);
            var texture = FXGL.getAssetLoader().loadTexture("cards/character/b2-isaac.png");
            texture.setFitWidth(texture.getWidth() * 0.2);
            texture.setFitHeight(texture.getHeight() * 0.2);
            texture.setEffect(shadow);
            texture.setStyle("-fx-border-radius: 100pt; -fx-background-radius: 100pt;");
//            var w = texture.getFitWidth();
//            var h = texture.getFitHeight();
            var y = getSettings().getHeight() - texture.getFitHeight() - 2*OFFSET;
//            var pane = new StackPane(texture);
//            pane.setStyle("-fx-border-radius: 100; -fx-background-radius: 100;");
            addUINode(texture, OFFSET, y);
//            texture.setOnMouseEntered(event -> {
//                texture.setScaleX(1.25);
//                texture.setScaleY(1.25);
//            });
//            texture.setOnMouseExited(event -> {
//                texture.setScaleX(1);
//                texture.setScaleY(1);
//            });
        });
        addUINode(lbl1, midX - lbl1.getPrefWidth()/2d, midY);
        var lbl2 = initLabel("Multiplayer");
        addUINode(lbl2, midX - lbl1.getPrefWidth()/2d, midY+100);
        var lbl3 = initLabel("Options");
        addUINode(lbl3, midX - lbl1.getPrefWidth()/2d, midY+200);
    }

    public Label initLabel(String text) {
        var lbl = new Label(text);
        lbl.setAlignment(Pos.CENTER);
        lbl.setTextFill(Color.WHITE);
        lbl.setFont(Font.font("EdmundMcMillen_v2", 32d));
        lbl.setPrefWidth(300);
        lbl.setOnMouseEntered((e) -> lbl.setStyle("-fx-underline: true;"));
        lbl.setOnMouseExited((e) -> lbl.setStyle(""));
        return lbl;
    }
}