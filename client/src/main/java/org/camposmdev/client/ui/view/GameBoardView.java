package org.camposmdev.client.ui.view;

import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

public class GameBoardView implements View {
    private final GameView view;

    public GameBoardView(int zIndex) {
        Texture texture = FXGL.texture("board.jpg");
        texture.setFitWidth(FXGL.getAppWidth());
        texture.setFitHeight(FXGL.getAppHeight());
        view = new GameView(texture, zIndex);
    }

    @Override
    public void render() {
        FXGL.getGameScene().addGameView(view);
    }

    @Override
    public void dispose() {
        FXGL.getGameScene().removeGameView(view);
    }
}
