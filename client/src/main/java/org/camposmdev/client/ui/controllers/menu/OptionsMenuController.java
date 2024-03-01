package org.camposmdev.client.ui.controllers.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.camposmdev.client.ui.FXUtil;
import org.camposmdev.client.ui.controllers.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class OptionsMenuController extends FXController implements Initializable {
    @FXML VBox root;
    @FXML CheckBox fullScreenCheckBox;
    @FXML Slider sfxSlider, musicSlider;

    private Runnable backCallback;

    public void setBackCallback(Runnable backCallback) {
        this.backCallback = backCallback;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullScreenCheckBox.setSelected(getSettings().getFullScreen().getValue());
        sfxSlider.setValue(getSettings().getGlobalSoundVolume());
        musicSlider.setValue(getSettings().getGlobalMusicVolume());
        sfxSlider.valueProperty().addListener((ov, arg0, arg1) -> getSettings().setGlobalSoundVolume(arg1.doubleValue()));
        musicSlider.valueProperty().addListener((ov, arg0, arg1) -> getSettings().setGlobalMusicVolume(arg1.doubleValue()));
    }

    public void handleFullScreen(ActionEvent event) {
        var flag = ((CheckBox)event.getSource()).isSelected();
        getSettings().getFullScreen().set(flag);
    }

    public void handleSfxSliderMouseReleased() {
        FXUtil.playDeathSound(null);
    }

    public void handleBack() {
        /* hide the option menu */
        setHidden(true);
        /* show main menu */
        backCallback.run();
    }

    public void setHidden(boolean flag) {
        root.setVisible(!flag);
        root.setDisable(flag);
    }
}
