package org.camposmdev.client.ui.controllers.menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
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

//    double orgSceneX, orgSceneY;
//    double orgTranslateX, orgTranslateY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable callback = () -> setHidden(false);
        singleplayerMenuController.setCallback(() -> {

        });
        multiplayerMenuController.setHostGameCallback(() -> {
            logoPane.setVisible(false);
            var ui = FXUtil.loadUI("Nightmare.fxml");
            assert ui != null;
            var background = (StackPane) ui.getRoot();
            background.setScaleX(3);
            background.setScaleY(3);
            background.setTranslateX(root.getWidth()/2d - background.getPrefWidth()/2d);
            var test = (StackPane) FXUtil.loadFXML("Bubble.fxml");
            assert test != null;
            test.setScaleX(3);
            test.setScaleY(3);
            test.setTranslateX(root.getWidth()/2d - test.getPrefWidth()/2);
            test.setTranslateY(root.getHeight()/2d - test.getPrefHeight()/2d);

//            var css = getAssetLoader().loadCSS("global-chat.css");
//            System.out.println(css);
//            System.out.println(css.getExternalForm());
//            System.out.println(css.component1());
//            TextArea textArea = new TextArea("""
//                    The lazy brown
//                    fox jumps over the
//                    long fence
//                    """);
//
//            textArea.setFocusTraversable(false);
////            textArea.getStylesheets().add(css.getExternalForm());
////            textArea.setStyle("""
//
////                    """);
//            textArea.setContextMenu(null);
//            textArea.setEditable(false);
//            textArea.setTranslateX(test.getTranslateX());
//            textArea.setTranslateY(test.getTranslateY()-300);
//            textArea.setOnMousePressed((MouseEvent event) -> {
//                orgSceneX = event.getSceneX();
//                orgSceneY = event.getSceneY();
//                orgTranslateX = textArea.getTranslateX();
//                orgTranslateY = textArea.getTranslateY();
//            });
//
//            textArea.setOnMouseDragged((MouseEvent event) -> {
//                double offsetX = event.getSceneX() - orgSceneX;
//                double offsetY = event.getSceneY() - orgSceneY;
//                double newTranslateX = orgTranslateX + offsetX;
//                double newTranslateY = orgTranslateY + offsetY;
//
//                textArea.setTranslateX(newTranslateX);
//                textArea.setTranslateY(newTranslateY);
//            });
//            var dummy1 = new Pane();
//            var dummy2 = new StackPane(textArea, dummy1);
//            textArea.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
//                dummy1.requestFocus(); // Request focus to keep the TextArea focusable
//                event.consume(); // Consume the event to prevent focus change
//            });
            root.getChildren().addAll(background, test);
        });
        multiplayerMenuController.setCancelCallback(callback);
        optionsMenuController.setBackCallback(callback);
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
        setHidden(true);
        singleplayerMenuController.setHidden(false);
    }

    public void handleMP() {
        setHidden(true);
        multiplayerMenuController.setHidden(false);
    }

    public void handleOptions() {
        setHidden(true);
        optionsMenuController.setHidden(false);
    }

    public void handleExit() {
        getDialogService().showConfirmationBox("Are you sure you want me to die? :(", kill -> {
            if (kill) {
                FXUtil.playDeathSound(() -> {
                    getGameController().exit();
                    API.get().close();
                });
            }
        });
    }

    public void setHidden(boolean flag) {
        menuBox.setVisible(!flag);
        menuBox.setDisable(flag);
    }
}
