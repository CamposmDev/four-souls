package io.github.camposmdev.foursouls.app.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import io.github.camposmdev.foursouls.model.ui.FXController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutController extends FXController {
    @FXML Label version;
    @FXML Hyperlink dev, fxgl;
    @Override
    public void init() {
        version.setText(String.format("Version %s", FXGL.getSettings().getVersion()));
    }

    public void dev() throws URISyntaxException, IOException {
        final String DEV = "https://github.com/CamposmDev";
        Desktop.getDesktop().browse(new URI(DEV));
    }

    public void fxgl() throws URISyntaxException, IOException {
        final String FXGL = "https://github.com/AlmasB/FXGL";
        Desktop.getDesktop().browse(new URI(FXGL));
    }
}
