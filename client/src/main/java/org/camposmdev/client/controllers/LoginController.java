package org.camposmdev.client.controllers;

import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
            FXGL.getNotificationService().pushNotification(msg);
            /* login good, remove login screen */
            var board = getAssetLoader().loadTexture("board.jpg");
            board.setFitWidth(getSettings().getWidth());
            board.setFitHeight(getSettings().getHeight());
            FXGL.animationBuilder().duration(Duration.millis(500)).translate(root).to(new Point2D(root.getTranslateX(), -root.getHeight()*2)).buildAndPlay();
            FXGL.animationBuilder().onFinished(() -> {
                removeUINode(root);
                var midX = getSettings().getWidth() / 2d;
                var midY = getSettings().getHeight() / 2d;
//                VBox vbox = new VBox();
//                String[] arr = new String[] {"hello there", "hi", "ejrogejrig", "ejriogjeorg", "erjgoierjgo"};
//                for (int i = 0; i < 5; i++) {
//                    for (var s : arr) {
//                        var txt = new Text("Camposm: " + s);
//                        txt.setWrappingWidth(300);
//                        txt.setFill(Color.WHITE);
//                        vbox.getChildren().add(txt);
//                    }
//                }
//
//                var x = FXGL.getAssetLoader().loadCSS("controls.css");
//                ScrollPane scrollPane = new ScrollPane();
//                scrollPane.getStylesheets().add(x.getExternalForm());
//                scrollPane.getStyleClass().add("global-chat");
//                scrollPane.setPrefWidth(300);
//                scrollPane.setPrefHeight(300);
//                scrollPane.setContent(vbox);
                var chatPane = (VBox) FXManager.loadUI("GlobalChat.fxml");
                assert chatPane != null;
                addUINode(chatPane, getSettings().getWidth() - chatPane.getPrefWidth() - 4, getSettings().getHeight() - chatPane.getPrefHeight()-4);

                var logo = getAssetLoader().loadTexture("logo.png");
                logo.setScaleX(0.5);
                logo.setScaleY(0.5);
                addUINode(logo, midX - logo.getWidth()/2d, -120);
                var lbl1 = initLabel("Singleplayer");
                var lbl2 = initLabel("Multiplayer");
                var lbl3 = initLabel("Options");
                var lbl4 = initLabel("Exit");
                lbl4.setOnMouseClicked(e -> getDialogService().showConfirmationBox("Are you sure you want me to die? :(", answer -> {
                    if (answer) {
                        final int NUM_OF_DEATHS = 3;
                        /* fetch a random file in the folder */
                        int i = (int)(Math.random() * NUM_OF_DEATHS) + 1;
                        var url = getClass().getClassLoader().getResource("./assets/sounds/death/death" + i + ".wav");
                        if (url != null) {
                            var str = url.toString();
                            var media = new Media(str);
                            var mp = new MediaPlayer(media);
                            mp.play();
                            mp.setOnEndOfMedia(() -> {
                                getGameController().exit();
                                FSClient.getInstance().close();
                            });
                        }
                    }
                }));
                VBox box = new VBox(12);
                box.getChildren().addAll(lbl1, lbl2, lbl3, lbl4);
                box.setPrefWidth(300);
                addUINode(box, midX - box.getPrefWidth()/2d, midY);
                lbl1.setOnMouseClicked(e -> {
                    getGameScene().clearUINodes();
                    addUINode(board, 0, 0);
                });

            }).duration(Duration.millis(500)).fadeOut(root).buildAndPlay();
        })).onFailure(e -> Platform.runLater(() -> FXGL.getNotificationService().pushNotification(e.getMessage())));
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