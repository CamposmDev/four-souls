package io.github.camposmdev.foursouls.app.game.ui.view;

import javafx.scene.Node;

public interface View {
    void render();
    void dispose();
    Node content();
}
