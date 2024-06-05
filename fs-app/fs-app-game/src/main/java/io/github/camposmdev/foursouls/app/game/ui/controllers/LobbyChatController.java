package io.github.camposmdev.foursouls.app.game.ui.controllers;

import io.github.camposmdev.foursouls.app.game.api.API;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import io.github.camposmdev.foursouls.model.api.BusEvent;
import io.github.camposmdev.foursouls.model.ui.FXController;

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
