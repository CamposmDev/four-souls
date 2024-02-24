package org.camposmdev.client.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.camposmdev.client.api.FSClient;
import org.camposmdev.model.MessageType;

public class GlobalChatController {
    @FXML
    ScrollPane scrollPane;
    @FXML
    VBox chatBox;

    public void initialize() {
        FSClient.getInstance().subscribeTo(MessageType.G_CHAT.name()).handler(msg -> Platform.runLater(() -> updateUI((String) msg.body())));
        Platform.runLater(() -> chatBox.heightProperty().addListener((ov, t1, t2) -> scrollPane.setVvalue(1.0)));
    }

    private void updateUI(String body) {
        var t = new Text(body);
        t.setWrappingWidth(300);
        t.setFill(Color.WHITE);
        chatBox.getChildren().add(t);
    }

    public void sendMessage(ActionEvent e) {
        var tf = (TextField) e.getSource();
        var msg = tf.getText();
        if (msg.isBlank()) return;
        tf.clear();
        FSClient.getInstance().sendGlobalMessage(msg);
    }
}
