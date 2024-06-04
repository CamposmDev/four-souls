package io.github.camposmdev.foursouls.app.game.ui.view;

import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.Node;

/**
 * The background of the tabletop game
 */
public class PlayMatView implements View {
    private final GameView view;

    public PlayMatView(int zIndex) {
        /* TODO - maybe one day I can find the holy and tainted version */
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

    @Override
    public Node content() {
        return view.getNode();
    }
}
