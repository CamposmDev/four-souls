package io.github.camposmdev.foursouls.app.game.ui.controllers.menu;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import io.github.camposmdev.foursouls.core.ui.FXController;

public class SingleplayerMenuController extends FXController {
    @FXML
    AnchorPane root;

    private Runnable cancelCallback;

    public void setCancelCallback(Runnable cancelCallback) {
        this.cancelCallback = cancelCallback;
    }

    public void cancel() {
        cancelCallback.run();
        hide(true);
    }

    public void hide(boolean flag) {
        root.setVisible(!flag);
        root.setDisable(flag);
    }
}
