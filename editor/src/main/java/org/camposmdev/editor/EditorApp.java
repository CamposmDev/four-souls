package org.camposmdev.editor;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import javafx.scene.paint.Color;
import org.camposmdev.editor.ui.factory.EditorSceneFactory;
import org.camposmdev.editor.ui.workspace.Workspace;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.factory.DialogFactory;
import java.util.List;

public class EditorApp extends GameApplication {
    public static void main(String[] args) {
        GameApplication.launch(args);
    }
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Four Souls Editor");
        settings.setVersion("1.1.1");
        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setSceneFactory(new EditorSceneFactory());
        settings.setFullScreenAllowed(true);
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
        settings.setAppIcon("icons/rebirth.png");
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        settings.setCSSList(List.of("dark.css"));
        settings.setGameMenuEnabled(false);
        settings.setMainMenuEnabled(false);
    }

    @Override
    protected void onPreInit() {
        Model.instance();
    }

    @Override
    protected void initUI() {
        FXGL.getNotificationService().setBackgroundColor(Color.web("#2D2D30"));
        FXGL.getNotificationService().setTextColor(Color.WHITE);
        var editor = new Workspace(FXGLForKtKt.getAppWidth(), FXGLForKtKt.getAppHeight());
        FXGLForKtKt.addUINode(editor.getContent());
        FXGL.getPrimaryStage().setOnCloseRequest(e -> {
            DialogFactory.instance().showExitBox();
            e.consume();
        });
    }
}
