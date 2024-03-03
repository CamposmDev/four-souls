package org.camposmdev.client.ui.controllers;

import com.almasb.fxgl.dsl.FXGL;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.camposmdev.client.model.UserContext;
import org.camposmdev.client.net.API;
import org.camposmdev.client.ui.FXUtil;
import org.camposmdev.client.ui.controllers.sprite.BubbleController;
import org.camposmdev.model.BusEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController extends FXController implements Initializable {
    @FXML AnchorPane root;
    @FXML StackPane nightmare, bubble;
    @FXML VBox playerBox;
    @FXML TextField tfMessage;
    @FXML Text textLeave, textStart, textGameId;
    @FXML BubbleController bubbleController;
    @FXML VBox chat;
    @FXML
    LobbyChatController chatController;

//    private UI chat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bubbleController.setOnFinished(event -> showChat());
        updateTextGameId();
        updatePlayerBox(UserContext.get().getCurrentLobby().getPlayerNames());
        API.get().subscribeTo(BusEvent.UPDATE_LOBBY).handler(msg -> {
            var obj = (JsonObject) msg.body();
            System.out.println(obj);
            var outliar = UserContext.get().setCurrentLobby(obj);
            var names = UserContext.get().getCurrentLobby().getPlayerNames();
            Platform.runLater(() -> updatePlayerBox(names));
            Platform.runLater(() -> {
                if (outliar != null)
                    chatController.annouce(outliar);
//                    ((LobbyChatController) chat.getController()).annouce(outliar);
            });
        });
        /* TODO - Remove start game button if the user is NOT the host */
        if (!UserContext.get().isHosting()) {
            textStart.setDisable(true);
            textStart.setVisible(false);
        }
    }

    private void updateTextGameId() {
        textGameId.setText(UserContext.get().getCurrentLobby().getId());
    }

    private void updatePlayerBox(String[] names) {
        playerBox.getChildren().clear();
        for (String name : names) {
            var text = new Text(name);
            text.getStyleClass().add("menu-label");
            HBox hBox = new HBox(text);
            playerBox.getChildren().add(hBox);
        }
    }

    private void showChat() {
        /* After the bubbles finished their animation display the lobby chat */

//        chat = FXUtil.loadUI("LobbyChat.fxml");
//        assert chat != null;
//        var chatPane = chat.getRoot();
//        root.getChildren().add(chatPane);
//        final var TOP_ANCHOR = 80d;
//        final var INLINE_ANCHOR = 650d;
//        AnchorPane.setTopAnchor(chatPane, TOP_ANCHOR);
//        AnchorPane.setLeftAnchor(chatPane, INLINE_ANCHOR);
//        AnchorPane.setRightAnchor(chatPane, INLINE_ANCHOR);
        final var TIME = Duration.seconds(.25);
//        FXUtil.animation().fadeIn(chatPane)
//                .duration(TIME).onFinished(e -> {
//                    /* After the lobby chat finished their animation, show the message field*/
//                    FXUtil.animation().fadeIn(playerBox).duration(TIME).build().play();
//                    FXUtil.animation().fadeIn(tfMessage).duration(TIME).build().play();
//                    FXUtil.animation().fadeIn(textLeave).duration(TIME).build().play();
//                    FXUtil.animation().fadeIn(textStart).duration(TIME).build().play();
//                }).build().play();
        FXUtil.animation().fadeIn(chat).duration(TIME).onFinished(e -> {
                    FXUtil.animation().fadeIn(playerBox).duration(TIME).build().play();
                    FXUtil.animation().fadeIn(tfMessage).duration(TIME).build().play();
                    FXUtil.animation().fadeIn(textLeave).duration(TIME).build().play();
                    FXUtil.animation().fadeIn(textStart).duration(TIME).build().play();
        }).build().play();
    }

    public void startGame() {
        /* TODO - Implement Multiplayer Game */
        System.err.println("This is where the fun begins.");
    }

    public void leaveGame() {
        API.get().leaveLobby(UserContext.get().getCurrentLobby().getId());
    }

    public void copyGameId(MouseEvent event) {
        var id = ((Text) event.getSource()).getText();
        var content = new ClipboardContent();
        content.putString(id);
        Clipboard.getSystemClipboard().setContent(content);
        FXGL.getNotificationService().pushNotification("Copied Game ID");
    }

    public void handleSendMessage(ActionEvent event) {
        var message = ((TextField) event.getSource()).getText();
        /* send message to players currently in lobby */
        API.get().sendLobbyMessage(message);
        ((TextField) event.getSource()).clear();
    }
}
