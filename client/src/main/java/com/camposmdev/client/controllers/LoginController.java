package com.camposmdev.client.controllers;

import com.almasb.fxgl.dsl.FXGL;
import com.camposmdev.client.net.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Locale;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class LoginController {
    @FXML AnchorPane root;
    @FXML TextField tfName, tfPassword;

    public void initialize() {
        Platform.runLater(() -> {
            var stage = (Stage) root.getScene().getWindow();
            stage.getIcons().add(new Image("assets/textures/icons/icon-soul-circle.png"));
            tfName.requestFocus();
        });
    }

    public void login() {
        var name = tfName.getText();
        var password = tfPassword.getText();
        if (Client.getInstance().login(name, password)) {
            /* login success, remove login screen */
            FXGL.animationBuilder().duration(Duration.millis(500)).translate(root).to(new Point2D(root.getTranslateX(), -root.getHeight()*2)).buildAndPlay();
            FXGL.animationBuilder().onFinished(() -> {
                removeUINode(root);
                var midX = getSettings().getWidth() / 2d;
                var midY = getSettings().getHeight() / 2d;
                var logo = FXGL.getAssetLoader().loadTexture("logo.png");
                logo.setScaleX(0.5);
                logo.setScaleY(0.5);
                addUINode(logo, midX - logo.getWidth()/2d, -120);
                var t1 = new Label("Singleplayer");
                t1.setAlignment(Pos.CENTER);
                t1.setFont(Font.font("EdmundMcMillen_v2", 32d));
                t1.setTextFill(Color.WHITE);
                t1.setPrefWidth(300);
                t1.setOnMouseEntered((e) -> t1.setStyle("-fx-underline: true;"));
                t1.setOnMouseExited((e) -> t1.setStyle(""));
                addUINode(t1, midX - t1.getPrefWidth()/2d, midY);
//                t1.setTranslateX(t1.getTranslateX() - t1.getLayoutX()/2d);
            }).duration(Duration.millis(500)).fadeOut(root).buildAndPlay();
        } else {

        }
    }
}