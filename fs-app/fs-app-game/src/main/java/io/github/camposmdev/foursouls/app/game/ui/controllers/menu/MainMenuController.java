package io.github.camposmdev.foursouls.app.game.ui.controllers.menu;

import com.almasb.fxgl.ui.UI;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import io.github.camposmdev.foursouls.app.game.model.Model;
import io.github.camposmdev.foursouls.app.game.api.API;
import io.github.camposmdev.foursouls.model.fx.FXUtil;
import io.github.camposmdev.foursouls.model.fx.FXController;
import io.github.camposmdev.foursouls.model.net.BusEvent;
import io.github.camposmdev.foursouls.model.fx.Log;

import java.net.URL;
import java.util.ResourceBundle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class MainMenuController extends FXController implements Initializable {
    @FXML
    AnchorPane root;
    @FXML
    StackPane logoPane;
    @FXML
    VBox menuBox;
    @FXML SingleplayerMenuController singleplayerMenuController;
    @FXML MultiplayerMenuController multiplayerMenuController;
    @FXML
    OptionsMenuController optionsMenuController;

    private UI lobby;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable cancelCallback = () -> hideAll(false);
        Runnable showLobby = () -> {
            /* user hosts or joins game */
            multiplayerMenuController.hide(true);
            hideAll(true);
            this.lobby = FXUtil.loadUI("Lobby.fxml");
            root.getChildren().add(lobby.getRoot());
        };
        Runnable removeLobby = () -> {
            /* user leaves lobby */
            multiplayerMenuController.hide(false);
            root.getChildren().remove(lobby.getRoot());
            this.lobby = null;
            hideLogo(false);
            Model.instance().setCurrentLobby(null);
        };
        singleplayerMenuController.setCancelCallback(cancelCallback);
        multiplayerMenuController.setCancelCallback(cancelCallback);
        optionsMenuController.setBackCallback(cancelCallback);
        API.get().subscribeTo(BusEvent.SHOW_LOBBY).handler(msg -> {
            Model.instance().setCurrentLobby((JsonObject) msg.body());
            Platform.runLater(showLobby);
        });
        Log.debug("Subscribed to " + BusEvent.SHOW_LOBBY);
        API.get().subscribeTo(BusEvent.REMOVE_LOBBY).handler(msg -> {
            var obj = (JsonObject) msg.body();
            if (obj.containsKey("message"))
                Platform.runLater(() -> getDialogService().showMessageBox(obj.getString("message")));
            Platform.runLater(removeLobby);
        });
        Log.debug("Subscribed to " + BusEvent.REMOVE_LOBBY);
    }

    public void handleSP() {
        /*
         * TODO - Character Selection
         *  Hide the title screen and then go to character selection
         *  Keep the background, and give the player the option to select their character
         *  Give them two character cards to choose from that is picked randomly from the character pool
         *  Give an option to see their eternal base
         *  Once chosen display the board
         */
//        hideAll(true);
//        singleplayerMenuController.hide(false);
        getGameController().startNewGame();
    }

    public void handleMP() {
        hideMainMenu(true);
        multiplayerMenuController.hide(false);
    }

    public void handleOptions() {
        hideMainMenu(true);
        optionsMenuController.hide(false);
    }

    public void handleExit() {
        getDialogService().showConfirmationBox("Are you sure you want me to die? :(", kill -> {
            if (kill) {
                FXUtil.playDeathSFX(() -> {
                    getGameController().exit();
                    API.get().close();
                });
            }
        });
    }

    public void hideMainMenu(boolean flag) {
        menuBox.setVisible(!flag);
        menuBox.setDisable(flag);
    }

    public void hideLogo(boolean flag) {
        logoPane.setVisible(!flag);
    }

    public void hideAll(boolean flag) {
        hideLogo(flag);
        hideMainMenu(flag);
    }
}
