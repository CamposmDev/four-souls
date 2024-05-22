package org.camposmdev.client.ui.controllers.menu;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.camposmdev.client.api.API;
import org.camposmdev.util.FXController;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

public class MultiplayerMenuController extends FXController {
    @FXML
    VBox menuBox;
    private Runnable cancelCallback;

    public void setCancelCallback(Runnable cancelCallback) {
        this.cancelCallback = cancelCallback;
    }

    public void handleHostGame() {
        API.get().hostGame();
    }

    public void handleJoinGame() {
        getDialogService().showInputBoxWithCancel("Invite Code", s -> !s.isBlank(), gameId -> {
            /* try to the join the game */
            if (!gameId.isBlank()) API.get().joinLobby(gameId);
        });
    }

    public void handleCancel() {
        /* hide multiplayer menu */
        hide(true);
        /* show main menu */
        cancelCallback.run();
    }

    public void hide(boolean flag) {
        menuBox.setVisible(!flag);
        menuBox.setDisable(flag);
    }
}
