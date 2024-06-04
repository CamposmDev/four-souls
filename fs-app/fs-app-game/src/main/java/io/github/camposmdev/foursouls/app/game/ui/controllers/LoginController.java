package io.github.camposmdev.foursouls.app.game.ui.controllers;

import com.almasb.fxgl.app.ApplicationMode;
import io.github.camposmdev.foursouls.app.game.api.API;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;

import javafx.util.Duration;
import io.github.camposmdev.foursouls.model.fx.FXController;
import io.github.camposmdev.foursouls.model.fx.FXUtil;

import java.net.URL;
import java.util.ResourceBundle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class LoginController extends FXController implements Initializable {
    @FXML AnchorPane root;
    @FXML TextField tfName, tfPassword;

    private Pane parent;
    public void setParent(Pane parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* if in developer mode, automatically login */
        if (getSettings().getApplicationMode().equals(ApplicationMode.DEBUG)) {
            tfName.setText("Camposm");
            tfPassword.setText("password");
        }
    }

    public void login() {
        var username = tfName.getText();
        var password = tfPassword.getText();
        API.get().login(username, password).onSuccess(msg -> Platform.runLater(() -> {
            final var TIME = Duration.millis(500);
            /* login good, remove login screen */
            FXUtil.animation().translate(root).to(new Point2D(root.getTranslateX(), root.getHeight()*2)).duration(TIME).build().play();
            FXUtil.animation().fadeOut(root).duration(TIME).onFinished(event -> {
                parent.getChildren().remove(root);
                showMainMenu();
            }).build().play();

        })).onFailure(e -> Platform.runLater(() -> getNotificationService().pushNotification(e.getMessage())));
    }

    public void showMainMenu() {
        var menuUI = FXUtil.loadUI("MainMenu.fxml");
        assert menuUI != null;
        parent.getChildren().add(menuUI.getRoot());
    }
}