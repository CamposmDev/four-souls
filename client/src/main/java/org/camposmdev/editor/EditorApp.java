package org.camposmdev.editor;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.Editor;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class EditorApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setScaleAffectedOnResize(true);
        settings.setManualResizeEnabled(true);
        settings.setTitle("Four Souls Editor");
    }

    @Override
    protected void onPreInit() {
        Model.instance();
    }

    @Override
    protected void initUI() {
        var editor = new Editor(getAppWidth(), getAppHeight());
        FXGL.addUINode(editor.getContent());
    }
}
