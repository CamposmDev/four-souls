package org.camposmdev.client.controllers;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        FSClient.getInstance().bus().consumer(MessageType.GCHAT.name()).handler(msg -> {
            Platform.runLater(() -> updateUI((String) msg.body()));
        });
        Platform.runLater(() -> {

        });
    }

    private void updateUI(String body) {
        var t = new Text(body);
        t.setWrappingWidth(300);
        t.setFill(Color.WHITE);
        chatBox.getChildren().add(t);
        scrollPane.setVvalue(1.0);
    }

    @Deprecated
    public JsonObject[] generateMatrix(int size) {
        String[] usernames = {"john_doe", "alice_smith", "bob_jones", "emily_wang", "michael_clark"};
        String[] messages = {"Hello, how are you?", "I'm running late, see you soon!", "What do you think about this?", "Just finished my presentation.", "Heading out for lunch."};

        JsonObject[] matrix = new JsonObject[size];

        for (int i = 0; i < size; i++) {
            String username = usernames[(int) (Math.random() * usernames.length)];
            String message = messages[(int) (Math.random() * messages.length)];
            JsonObject jsonObject = new JsonObject()
                    .put("username", username)
                    .put("message", message);
            matrix[i] = jsonObject;
        }

        return matrix;
    }

    public void sendMessage(ActionEvent e) {
        var tf = (TextField) e.getSource();
        var msg = tf.getText();
        tf.clear();
        /* TODO Send message to server */
        FSClient.getInstance().sendGlobalMessage(msg);
    }
}
