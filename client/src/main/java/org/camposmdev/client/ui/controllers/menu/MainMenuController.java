package org.camposmdev.client.ui.controllers.menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import org.camposmdev.client.net.API;
import org.camposmdev.client.ui.FXUtil;
import org.camposmdev.client.ui.controllers.FXController;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable cancelCallback = () -> setHideMainMenu(false);
        singleplayerMenuController.setCallback(() -> {

        });
        multiplayerMenuController.setHostGameCallback(() -> {
            /*
            * TODO - Update UI to display game lobby
            * */
            setHideAll(true);
            var ui = FXUtil.loadFXML("Lobby.fxml");
            assert ui != null;
            root.getChildren().addAll(ui);
        });
        multiplayerMenuController.setCancelCallback(cancelCallback);
        optionsMenuController.setBackCallback(cancelCallback);
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
        setHideAll(true);
        singleplayerMenuController.setHidden(false);
    }

    public void handleMP() {
        setHideMainMenu(true);
        multiplayerMenuController.setHidden(false);
    }

    public void handleOptions() {
        setHideMainMenu(true);
        optionsMenuController.setHidden(false);
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

    public void setHideMainMenu(boolean flag) {
        menuBox.setVisible(!flag);
        menuBox.setDisable(flag);
    }

    public void setHideAll(boolean flag) {
        logoPane.setVisible(!flag);
        setHideMainMenu(flag);
    }
}
