package org.camposmdev.client.controllers;

import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.camposmdev.client.api.FSClient;
import org.camposmdev.client.ui.FXManager;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class LoginController {
    @FXML AnchorPane root;
    @FXML TextField tfName, tfPassword;

    public void login() {
        var name = tfName.getText();
        var password = tfPassword.getText();
        FSClient.getInstance().login(name, password).onSuccess(msg -> Platform.runLater(() -> {
            /* login good, remove login screen */
            FXGL.animationBuilder().duration(Duration.millis(500)).translate(root).to(new Point2D(root.getTranslateX(), -root.getHeight()*2)).buildAndPlay();
            FXGL.animationBuilder().onFinished(() -> {
                removeUINode(root);
                initTitleScreen();
            }).duration(Duration.millis(500)).fadeOut(root).buildAndPlay();
        })).onFailure(e -> Platform.runLater(() -> FXGL.getNotificationService().pushNotification(e.getMessage())));
    }

    public void initTitleScreen() {
        var midX = getSettings().getWidth() / 2d;
        var midY = getSettings().getHeight() / 2d;
        var chatPane = (VBox) FXManager.loadUI("GlobalChat.fxml");
        assert chatPane != null;
        addUINode(chatPane, getSettings().getWidth() - chatPane.getPrefWidth() - 4, getSettings().getHeight() - chatPane.getPrefHeight()-4);

        var logo = getAssetLoader().loadTexture("logo.png");
        logo.setScaleX(0.5);
        logo.setScaleY(0.5);
        addUINode(logo, midX-logo.getWidth()/2d, midY-logo.getHeight());
        var lbl1 = initMenuLabel("Singleplayer");
        var lbl2 = initMenuLabel("Multiplayer");
        var lbl3 = initMenuLabel("Options");
        var lbl4 = initMenuLabel("Exit");
        lbl4.setOnMouseClicked(e -> getDialogService().showConfirmationBox("Are you sure you want me to die? :(", answer -> {
            if (answer) {
                FXManager.playDeathSound(() -> {
                    getGameController().exit();
                    FSClient.getInstance().close();
                });
            }
        }));
        VBox box = new VBox(12);
        box.getChildren().addAll(lbl1, lbl2, lbl3, lbl4);
        box.setPrefWidth(300);
        addUINode(box, midX - box.getPrefWidth()/2d, midY);
        var board = getAssetLoader().loadTexture("board.jpg");
        board.setFitWidth(getSettings().getWidth());
        board.setFitHeight(getSettings().getHeight());
        lbl1.setOnMouseClicked(e -> {
            getGameScene().clearUINodes();
            /**
             * TODO
             * Hide the title screen and then go to character selection
             * Keep the background, and give the player a choice to select their character
             * Give them two character cards to choose from that is picked randomly from the character pool
             * Give an option to see their eternal card
             * Once chosen display the board
             */
            addUINode(board, 0, 0);
        });
    }

    public Label initMenuLabel(String text) {
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