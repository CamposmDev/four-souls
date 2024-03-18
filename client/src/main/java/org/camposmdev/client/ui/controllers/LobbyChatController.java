package org.camposmdev.client.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.camposmdev.client.net.API;
import org.camposmdev.model.net.BusEvent;
import org.camposmdev.util.FXController;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyChatController extends FXController implements Initializable {
    @FXML VBox root;
    @FXML Pane dummy;
    @FXML TextArea ta;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        API.get().subscribeTo(BusEvent.LOBBY_CHAT).handler(msg ->
                ta.appendText((String) msg.body()));
    }

    public void taClicked(MouseEvent event) {
        dummy.requestFocus();
        event.consume();
    }

    public void annouce(String text) {
        ta.appendText(text);
    }
}
