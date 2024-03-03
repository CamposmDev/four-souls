package org.camposmdev.client.ui.controllers.menu;

import com.almasb.fxgl.ui.UI;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import org.camposmdev.client.model.ErrorRunnable;
import org.camposmdev.client.model.Log;
import org.camposmdev.client.model.UserContext;
import org.camposmdev.client.net.API;
import org.camposmdev.client.ui.FXUtil;
import org.camposmdev.client.ui.controllers.FXController;
import org.camposmdev.model.BusEvent;

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
            UserContext.get().setCurrentLobby(null);
        };
        singleplayerMenuController.setCancelCallback(cancelCallback);
        multiplayerMenuController.setCancelCallback(cancelCallback);
        optionsMenuController.setBackCallback(cancelCallback);
        API.get().subscribeTo(BusEvent.SHOW_LOBBY).handler(msg -> {
            UserContext.get().setCurrentLobby((JsonObject) msg.body());
            Platform.runLater(showLobby);
        });
        Log.debug("Subscribed to " + BusEvent.SHOW_LOBBY);
        API.get().subscribeTo(BusEvent.REMOVE_LOBBY).handler(msg -> {
            var obj = (JsonObject) msg.body();
            if (obj.containsKey("message"))
                Platform.runLater(new ErrorRunnable(obj.getString("message")));
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
         *  Give an option to see their eternal card
         *  Once chosen display the board
         */
        hideAll(true);
        singleplayerMenuController.hide(false);
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
