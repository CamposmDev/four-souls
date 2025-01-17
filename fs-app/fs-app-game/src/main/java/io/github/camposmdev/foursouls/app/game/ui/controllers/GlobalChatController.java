package io.github.camposmdev.foursouls.app.game.ui.controllers;

import io.github.camposmdev.foursouls.app.game.api.API;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import io.github.camposmdev.foursouls.core.api.BusEvent;

@Deprecated
public class GlobalChatController {
    @FXML
    ScrollPane scrollPane;
    @FXML
    VBox chatBox;

    public void initialize() {
        API.get().subscribeTo(BusEvent.GLOBAL_CHAT).handler(msg -> Platform.runLater(() -> updateUI((String) msg.body())));
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
        API.get().sendGlobalMessage(msg);
    }
}
