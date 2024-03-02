package org.camposmdev.client.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyChatController extends FXController implements Initializable {
    @FXML VBox root;
    @FXML Pane dummy;
    @FXML TextArea ta;
    public void taClicked(MouseEvent event) {
        dummy.requestFocus();
        event.consume();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* TODO - Have controller listen for lobby messages */
    }
}
