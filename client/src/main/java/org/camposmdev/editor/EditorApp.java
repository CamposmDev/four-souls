package org.camposmdev.editor;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.Workspace;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class EditorApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Four Souls Editor");
        settings.setVersion("1.0");
        settings.setWidth(1440);
        settings.setHeight(900);
        settings.setFullScreenAllowed(true);
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        settings.setCSSList(List.of());
        settings.setGameMenuEnabled(false);
        settings.setMainMenuEnabled(false);
    }

    @Override
    protected void onPreInit() {
        Model.instance();
    }

    @Override
    protected void initUI() {
        var editor = new Workspace(getAppWidth(), getAppHeight());
        addUINode(editor.getContent());
        FXGL.getPrimaryStage().setOnCloseRequest(e -> {
            DialogFactory.instance().showExitBox();
            e.consume();
        });
    }
}
