package org.camposmdev.client.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

public class FSSceneFactory extends SceneFactory {
    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new FSMainMenu(MenuType.MAIN_MENU);
    }

    @NotNull
    @Override
    public FXGLMenu newGameMenu() {
        /* TODO - Implement game menu */
        return super.newGameMenu();
    }

    @NotNull
    @Override
    public IntroScene newIntro() {
        /* TODO - Implement intro scene*/
        return super.newIntro();
    }

    @NotNull
    @Override
    public LoadingScene newLoadingScene() {
        /* TODO - Implement loading scene */
        return super.newLoadingScene();
    }

    @NotNull
    @Override
    public com.almasb.fxgl.app.scene.StartupScene newStartup(int width, int height) {
        return new FSStartupScene(width, height);
    }
}
