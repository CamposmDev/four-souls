package org.camposmdev.client.ui.controllers;

import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.camposmdev.client.ui.FXUtil;
import org.camposmdev.client.ui.controllers.sprite.BubbleController;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController extends FXController implements Initializable {
    @FXML
    AnchorPane root;
    @FXML
    StackPane nightmare, bubble;
    @FXML
    TextField tfMessage;
    @FXML BubbleController bubbleController;

    Runnable cancelGameCallback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bubbleController.setOnFinished(event -> showChat());
    }

    private void showChat() {
        /* After the bubbles finished their animation display the lobby chat */
        var ui = FXUtil.loadUI("LobbyChat.fxml");
        assert ui != null;
        var chatPane = ui.getRoot();
        root.getChildren().add(chatPane);
        final var TOP_ANCHOR = 80d;
        final var INLINE_ANCHOR = 650d;
        AnchorPane.setTopAnchor(chatPane, TOP_ANCHOR);
        AnchorPane.setLeftAnchor(chatPane, INLINE_ANCHOR);
        AnchorPane.setRightAnchor(chatPane, INLINE_ANCHOR);
        FXUtil.animation().fadeIn(chatPane)
                .duration(Duration.millis(250)).onFinished(e ->
                /* After the lobby chat finished their animation, show the message field*/
                FXUtil.animation().fadeIn(tfMessage)
                        .duration(Duration.millis(250)).build().play()).build().play();
    }

    public void startGame(ActionEvent event) {

    }

    public void cancelGame(ActionEvent event) {

    }


    public void handleSendMessage(ActionEvent event) {
        var message = ((TextField) event.getSource()).getText();
        /* send message to players currently in lobby */
        ((TextField) event.getSource()).clear();
    }
}
