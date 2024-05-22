package org.camposmdev.editor.ui.factory;

import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.StartupScene;
import org.camposmdev.editor.ui.EditorStartupScene;

public class EditorSceneFactory extends SceneFactory {
    @Override
    public StartupScene newStartup(int width, int height) {
        return new EditorStartupScene(width, height);
    }
}
