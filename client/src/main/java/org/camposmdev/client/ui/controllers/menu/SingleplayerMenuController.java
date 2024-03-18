package org.camposmdev.client.ui.controllers.menu;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.camposmdev.util.FXController;

public class SingleplayerMenuController extends FXController {
    @FXML
    AnchorPane root;

    private Runnable cancelCallback;

    public void setCancelCallback(Runnable cancelCallback) {
        this.cancelCallback = cancelCallback;
    }

    public void hide(boolean flag) {
        root.setVisible(!flag);
        root.setDisable(flag);
    }
}
