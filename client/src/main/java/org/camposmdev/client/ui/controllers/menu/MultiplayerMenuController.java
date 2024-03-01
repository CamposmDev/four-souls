package org.camposmdev.client.ui.controllers.menu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.camposmdev.client.net.API;
import org.camposmdev.client.ui.controllers.FXController;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

public class MultiplayerMenuController extends FXController {
    @FXML
    VBox menuBox;
    private Runnable hostGameCallback;
    private Runnable cancelCallback;

    public void setCancelCallback(Runnable cancelCallback) {
        this.cancelCallback = cancelCallback;
    }

    public void handleHostGame() {
        API.get().hostGame().onSuccess(gameId -> {
            /* try to join the game to be the host */
            API.get().joinGame(gameId).onSuccess(obj -> {
                /* TODO - Update UI to display game lobby */
                System.out.println(obj.toString());
                setHidden(true);
                Platform.runLater(hostGameCallback);
            });
        });
    }

    public void handleJoinGame() {
        getDialogService().showInputBoxWithCancel("Invite Code", s -> !s.isBlank(), s -> {
            /* tyr ot the join the game */
            API.get().joinGame(s).onSuccess(obj -> {
                /* TODO - Update UI to display game lobby */
                System.out.println(obj.toString());
                setHidden(true);
            }).onFailure(System.err::println);
        });
    }

    public void handleCancel() {
        /* hide multiplayer menu */
        setHidden(true);
        /* show main menu */
        cancelCallback.run();
    }

    public void setHidden(boolean flag) {
        menuBox.setVisible(!flag);
        menuBox.setDisable(flag);
    }

    public void setHostGameCallback(Runnable callback) {
        this.hostGameCallback = callback;
    }
}
