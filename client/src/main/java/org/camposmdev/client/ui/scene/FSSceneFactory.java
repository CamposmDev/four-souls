package org.camposmdev.client.ui.scene;

import com.almasb.fxgl.app.scene.*;
import org.camposmdev.client.ui.menu.FSGameMenu;
import org.camposmdev.client.ui.menu.FSMainMenu;
import org.camposmdev.util.Log;
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
        return new FSGameMenu(MenuType.GAME_MENU);
    }

    @NotNull
    @Override
    public IntroScene newIntro() {
        return new FSIntroScene();
    }

    @NotNull
    @Override
    public LoadingScene newLoadingScene() {
        /* TODO - Implement loading scene */
        return super.newLoadingScene();
    }

    @NotNull
    @Override
    public StartupScene newStartup(int width, int height) {
        return new FSStartupScene(width, height);
    }
}
