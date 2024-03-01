package org.camposmdev.client.ui.controllers.menu;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.camposmdev.client.ui.controllers.FXController;

public class SingleplayerMenuController extends FXController {
    @FXML
    AnchorPane root;

    private Runnable callback;

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public void setHidden(boolean flag) {
        root.setVisible(!flag);
        root.setDisable(flag);
    }
}
