package org.camposmdev.client.controllers;

import com.almasb.fxgl.audio.AudioType;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
            var board = getAssetLoader().loadTexture("board.jpg");
            board.setFitWidth(getSettings().getWidth());
            board.setFitHeight(getSettings().getHeight());
            FXGL.animationBuilder().duration(Duration.millis(500)).translate(root).to(new Point2D(root.getTranslateX(), -root.getHeight()*2)).buildAndPlay();
            FXGL.animationBuilder().onFinished(() -> {
                removeUINode(root);
                var midX = getSettings().getWidth() / 2d;
                var midY = getSettings().getHeight() / 2d;
                var logo = FXGL.getAssetLoader().loadTexture("logo.png");
                logo.setScaleX(0.5);
                logo.setScaleY(0.5);
                addUINode(logo, midX - logo.getWidth()/2d, -120);
                var lbl1 = initLabel("Singleplayer");
//                addUINode(lbl1, midX - lbl1.getPrefWidth()/2d, midY);
                var lbl2 = initLabel("Multiplayer");
//                addUINode(lbl2, midX - lbl1.getPrefWidth()/2d, midY+100);
                var lbl3 = initLabel("Options");
//                addUINode(lbl3, midX - lbl1.getPrefWidth()/2d, midY+200);
                var lbl4 = initLabel("Exit");
                lbl4.setOnMouseClicked(e -> {
                    getDialogService().showConfirmationBox("Are you sure you want me to die? :(", answer -> {
                        if (answer) {
                            final int NUM_OF_DEATHS = 3;
                            /* fetch a random file in the folder */
                            int i = (int)(Math.random() * NUM_OF_DEATHS) + 1;
                            String s = getClass().getClassLoader().getResource("./assets/sounds/death/death" + i + ".wav").toString();
                            var media = new Media(s);
                            var mp = new MediaPlayer(media);
                            mp.play();
                            mp.setOnEndOfMedia(() -> getGameController().exit());
                        }
                    });
                });
                VBox box = new VBox(12);
                box.getChildren().addAll(lbl1, lbl2, lbl3, lbl4);
                box.setPrefWidth(300);
                addUINode(box, midX - box.getPrefWidth()/2d, midY);
                lbl1.setOnMouseClicked(e -> {
                    getGameScene().clearUINodes();
                    addUINode(board, 0, 0);
                });

            }).duration(Duration.millis(500)).fadeOut(root).buildAndPlay();
        } else {

        }
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